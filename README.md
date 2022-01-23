# Módulo 1 - Projeto Avaliativo 1 (DevInHouse)

## **Requisitos da Aplicação**
* O sistema deverá ser desenvolvido em JAVA.
* O sistema deve seguir o Roteiro da Aplicação.
* O desenvolvimento das mensagens de saída do banco, esperando pela ação do usuário;
* Captura da interação do usuário via entrada padrão;
* O sistema deverá ser apresentado diretamente na linha de comando;

## **Roteiro da aplicação**
O banco DEVinMoney deseja automatizar todo o seu sistema de armazenamento de informações referentes aos seus clientes. O sistema deve conter os seguintes tipos de contas, cada uma com suas características:

**Conta corrente:** 
* Na conta corrente o cliente tem direito ao cheque especial, ou seja, poderá ficar negativo durante um período de tempo. O sistema deve definir o total do cheque especial, conforme a renda mensal do correntista. 
* Extrato das transações

**Conta poupança:** 
* Na conta poupança o cliente não tem direito ao cheque especial.
* Na conta poupança, o cliente poderá simular quanto o seu valor renderá em um determinado tempo, para isso, o cliente deve informar a quantidade de tempo (em meses) e a rentabilidade anual da poupança. Por exemplo, se o cliente deseja saber a rentabilidade em 6 meses é preciso calcular o rendimento proporcional ao valor mensal.
* Extrato das transações

**Conta investimento:**
* Neste tipo de conta, o cliente poderá escolher um tipo de investimento e o sistema deve apresentar o rendimento anual do investimento solicitado.
* O cliente poderá simular o rendimento do seu valor, levando em consideração o rendimento do investimento escolhido.

O banco também deseja manter um histórico das transações, que deverá armazenar (utilizar conceitos de composição):
* Dados Conta Origem
* Dados Conta Destino
* Valor
* Data (pegar a data e hora do sistema)

O sistema também deverá apresentar os seguintes relatórios:
* Listar todas as contas: Correntes, Poupanças ou Investimento
* Contas com saldo negativo
* Total do valor investido
* Todas as transações de um determinado cliente.

### **Restrições**
É importante que algumas transações não possam ser executadas em caso de problemas percebidos em suas operações:
* Transferência entre contas cujo montante supera o saldo acrescido do limite do cheque especial da conta de origem  
* Operações em momentos anteriores ao dia/hora da transação
* Transferências durante o final de semana (sábado ou domingo)
* Não é possível fazer transferências para si próprio










