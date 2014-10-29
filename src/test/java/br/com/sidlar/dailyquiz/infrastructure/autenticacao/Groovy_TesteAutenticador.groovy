package br.com.sidlar.dailyquiz.infrastructure.autenticacao

import br.com.sidlar.dailyquiz.domain.Membro
import br.com.sidlar.dailyquiz.domain.MembroNaoEncontradoException
import br.com.sidlar.dailyquiz.domain.MembroRepository
import br.com.sidlar.dailyquiz.domain.SenhaInvalidaException
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigestMd5
import br.com.sidlar.dailyquiz.infrastructure.digest.GeradorDigestSha256
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.mock.web.MockHttpSession

import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat
import static org.junit.Assert.fail
import static org.mockito.Matchers.anyString
import static org.mockito.Mockito.doThrow
import static org.mockito.Mockito.when

/**
 * @author Admilson
 */
class Groovy_TesteAutenticador{
    Autenticador autenticador;
    String email;
    String senha;

    @Mock MembroRepository membroRepository;
    @Mock Membro membro;

    @Before
    void setUp() {
        MockitoAnnotations.initMocks(this);
        autenticador = new Autenticador(membroRepository: membroRepository, geradorDigest: new GeradorDigestMd5())
        email = "jamesBond";
        senha = "123";
    }

    @Test(expected = MembroNaoEncontradoException.class)//Verify
    void "autenticação com email inexistente deve lançar exception"(){
        //Fixture
        doThrow(new MembroNaoEncontradoException("Membro não encontrado.")).when(membroRepository).buscaMembroPorEmail(anyString())

        //Sut
        autenticador.autentica(email, senha)
        fail("Autenticação com email inexistente deveria falhar.");
    }

    @Test(expected = SenhaInvalidaException.class)//Verify
    void "autenticação com senha inválida deve lançar exception"(){
        //Fixture
        String senhaInvalida = "azagaflais"
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro)
        when(membro.getSenha()).thenReturn(senhaInvalida)

        //Sut
        autenticador.autentica(email, senha)
        fail("Autenticação com senha do membro inválida deveria falhar.");
    }

    @Test(expected = RuntimeException.class)//Verify
    void "autenticação com implementação Digest incorreta deve lançar exception"(){
        //Fixture
        String senhaMembro = new GeradorDigestMd5().geraHashSenha("123")//implementação correta Md5
        autenticador.setGeradorDigest(new GeradorDigestSha256())//implementação incorreta Sha256
        when(membro.getSenha()).thenReturn(senhaMembro)
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro)

        //Sut
        autenticador.autentica(email, senha);
        fail("Autenticação com implementação Digest incorreta deveria falhar.");
    }

    @Test
    void "autenticação com email e senha valido deve armazenar membro em HttpSession"(){
        //Fixture
        MockHttpSession httpSession= new MockHttpSession()
        String senhaMembro = new GeradorDigestMd5().geraHashSenha("123")
        when(membro.getSenha()).thenReturn(senhaMembro)
        when(membroRepository.buscaMembroPorEmail(anyString())).thenReturn(membro)
        autenticador.setSession(httpSession)

        //Sut
        autenticador.autentica(email, senha)

        //Verify
        AutenticacaoMembro autenticacaoMembro = ((AutenticacaoMembro)httpSession.getAttribute("membroAutenticado"));
        assertThat(autenticacaoMembro.getMembro(), is(membro));
    }
}
