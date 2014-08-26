DailyQuiz
=========

DailyQuiz é um projeto educativo utilizado pela equipe de Sistemas da Sidlar e é constituído por diversas entregas com prazos bem definidos. Para visualizar as próximas entregas, acesse o wiki em https://github.com/sidlar/DailyQuiz/wiki.

Como preparar o ambiente de desenvolvimento?

- Instale o JDK 8
- Crie uma variável de ambiente '$JAVA_HOME' e especifique o diretório da instalação do JDK8.

- Instale ("unzip") o Apache Maven (http://maven.apache.org/)
- Crie uma variável de ambiente 'M2_HOME' e especifique o diretório de instalação do Maven.
- (Opcional) Adicione o path '$M2_HOME/bin' na variável de ambiente '$PATH'.

- Crie uma conta no github.com (se ainda não a possui).
- Faça o fork do projeto DailyQuiz (https://github.com/sidlar/DailyQuiz/) para a sua conta no GitHub.
- Faça o clone do projeto.

- Instale o Tomcat 8

- Copie os JARs em docs/libs para $TOMCAT_HOME/libs.

- Instale o MySQL (http://www.mysql.com/)
- Execute o arquivo de script 'docs/scripts/create_database_tables.sql' para criar o banco 'dailyquiz'.

- Instale o IntelliJ 14 EAP (http://confluence.jetbrains.com/display/IDEADEV/IDEA+14+EAP)

- Inicie o IntelliJ e abra o projeto clonado.
- Na aba 'Maven Projects', clique no botão 'Reimport All Maven Projects'.
- Adicione um 'Project SDK', apontando-o para a instalação do JDK 8.
- Em Run/Debug Configurations (ALT + SHIFT + F9), selecione 'Edit Configurations' (0) e adicione (botão '+') uma nova configuração 'Tomcat Server / Local'.
- Na aba 'Server', clique em 'Configure' e informe a home da instalação do tomcat.
- Na aba 'Deployment', adicione (botão '+') o artifact 'dailyquiz:war exploded'. Clique em 'Apply' e depois em 'Close'.
- Pronto, agora basta executar o Application Server: selecione a configuração Run/Debug 'DailyQuiz' e clique em 'Debug'.
