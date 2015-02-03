package br.com.sidlar.dailyquiz.infrastructure.autenticacao;

import br.com.sidlar.dailyquiz.domain.membro.Membro;
import br.com.sidlar.dailyquiz.domain.membro.MembroNaoEncontradoException;
import br.com.sidlar.dailyquiz.domain.membro.MembroRepository;
import br.com.sidlar.dailyquiz.domain.membro.SenhaInvalidaException;
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigestMd5;
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigestSha256;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TesteAutenticador {

    private Autenticador autenticador;
    private MockHttpSession httpSession;
    private String email;
    private String senha;

    @Mock private MembroRepository membroRepository;
    @Mock private Membro membro;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        httpSession = new MockHttpSession();
        autenticador = new Autenticador();
        autenticador.setMembroRepository(membroRepository);
        autenticador.setSession(httpSession);
        autenticador.setGeradorDigest(new GeradorDigestMd5());
        email = "jamesBond";
        senha = "123";
    }

    @Test(expected = MembroNaoEncontradoException.class)//Verify
    public void autenticacaoComEmailInexistente_deveLancarException(){
        //Fixture
        doThrow(new MembroNaoEncontradoException("Membro não encontrado.")).when(membroRepository).buscaMembroPorEmail(anyString());

        //Sut
        autenticador.autentica(email, senha);
        fail("Autenticação com email inexistente deveria falhar.");
    }

    @Test(expected = SenhaInvalidaException.class)//Verify
    public void autenticacaoComSenhaInvalida_deveLancarException(){
        //Fixture
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro);
        when(membro.getSenha()).thenReturn("azaflais");

        //Sut
        autenticador.autentica(email, senha);
        fail("Autenticação com senha do membro inválida deveria falhar.");
    }

    @Test(expected = RuntimeException.class)//Verify
    public void autenticacaoComImplementacaoDigestIncorreta_deveLancarException(){
        //Fixture
        String senhaMembro = new GeradorDigestMd5().geraHashSenha("123");//implementação correta Md5
        autenticador.setGeradorDigest(new GeradorDigestSha256());//implementação incorreta Sha256
        when(membro.getSenha()).thenReturn(senhaMembro);
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro);

        //Sut
        autenticador.autentica(email, senha);
        fail("Autenticação com implementação Digest incorreta deveria falhar.");
    }

    @Test
    public void autenticacaoComEmailESenhaValido_deveArmazenarMembroEmHttpSession(){
        //Fixture
        String senhaMembro = new GeradorDigestMd5().geraHashSenha("123");
        when(membro.getSenha()).thenReturn(senhaMembro);
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro);

        //Sut
        autenticador.autentica(email, senha);

        //Verify
        AutenticacaoMembro autenticacaoMembro = ((AutenticacaoMembro)httpSession.getAttribute("membroAutenticado"));
        assertThat(autenticacaoMembro.getMembro(), is(membro));
   }
}