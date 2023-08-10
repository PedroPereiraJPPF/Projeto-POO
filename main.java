// classe de cliente
class Cliente 
{
    private int id;
    private String nome, endereco, CPF;

    public Cliente(int id, String nome, String endereco, String CPF)
    {
        this.setId(id);
        this.setNome(nome);
        this.setEndereco(endereco);
        this.setCPF(CPF);
    }

    public void buscar(String Input){
        System.out.println("buscando");
    }

    public void cadastrar(Cliente cliente) {
        System.out.println("criando");
    }

    public void editar(int id, Cliente cliente) {
        System.out.println("editando");
    }

    public void excluir(int id) {
        System.out.println("excluindo");
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if(id > 0)
            this.id = id;
        else
            System.out.println("insira um id válido");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null)
            this.nome = nome;
        else
            System.out.println("insira um nome válido");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null)
            this.endereco = endereco;
        else
            System.out.println("insira um endereco válido");
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        if (CPF != null)
            this.CPF = CPF;
        else
            System.out.println("insira um CPF válido");
    }

}

// classe de peças
class Peça {
    private int id;
    private String nome;
    private double preco;
    private String fabricante;

    public Peça(int id, String nome, double preco, String fabricante) {
        setId(id);
        setNome(nome);
        setPreco(preco);
        setFabricante(fabricante);
    }

    public void buscar(String input) {
        System.out.println("Buscando...");
    }

    public void cadastrar(Peça peça) {
        System.out.println("Criando...");
    }

    public void editar(int id, Peça peça) {
        System.out.println("Editando...");
    }

    public void excluir(int id) {
        System.out.println("Excluindo...");
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id > 0)
            this.id = id;
        else
            System.out.println("ID inválido");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else
           System.out.println("nome inválido");
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco >= 0)
            this.preco = preco;
        else
            System.out.println("insira um preco válido");
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        if (fabricante != null)
            this.fabricante = fabricante;
        else
            System.out.println("insira um nome válido");
    }
}

class Relatorio {
    private int id;
    private String data; // futuramente será do tipo Date
    private double lucro;

    public Relatorio(int id, String data, double lucro) {
        setId(id);
        setData(data);
        setLucro(lucro);
    }

    public void gerarOrcamento() {
        System.out.println("Gerando orçamento...");
    }

    public void gerarArquivoPdf() {
        System.out.println("Gerando arquivo PDF...");
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        if (id > 0)
            this.id = id;
        else
            System.out.println("id inválido");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (data != null)
            this.data = data;
        else
            System.out.println("data inválida");
    }

    public double getLucro() {
        return lucro;
    }

    public void setLucro(double lucro) {
        if (lucro >= 0)
            this.lucro = lucro;
        else
            System.out.println("Lucro não pode ser negativo");
    }
}

class Automovel {
    
    private int id;
    private String marca, cor, placa, proprietarioCPF;
    private double quilometragem;

    public Automovel(int id, String proprietarioCPF, String marca, String cor, String placa, double quilometragem){

        setCor(cor);
        setId(id);
        setMarca(marca);
        setPlaca(placa);
        setProprietarioCPF(proprietarioCPF);
        setQuilometragem(quilometragem);    

    }

    public String getMarca(){return marca;}
    public void setMarca(String marca){
        if (this.marca == null || this.marca == " ") {System.out.println("Opção inválida");}
        else this.marca = marca;
    }

    public String getCor(){return cor;}
    public void setCor(String cor){
        if (this.cor == null || this.cor == " ") { System.out.println("Opção inválida");}
        else this.cor = cor;
    }

    public String getPlaca(){return placa;}
    public void setPlaca(String placa){
        if (this.placa == null || this.placa == " ") {System.out.println("Opção inválida");}
        else this.placa = placa;
    }

    public String getProprietarioCPF(){return proprietarioCPF;}
    public void setProprietarioCPF(String proprietarioCPF){
        if (this.proprietarioCPF == null || this.proprietarioCPF == " ") {System.out.println("Opção inválida");}
        else this.proprietarioCPF = proprietarioCPF;
    }

    public double getQuilometragem(){return quilometragem;}
    public void setQuilometragem(double quilometragem){
        if (this.quilometragem < 0) {System.out.println("Opção inválida");}
        else this.quilometragem = quilometragem;
    }

    public int getId(){return id;}
    public void setId(int id){
        if (this.id <= 0) {System.out.println("Opção inválida");}
        else this.id = id;
    }

    public void buscar(String input){System.out.println("Buscando automóvel...");}

    public void cadastrar(Automovel automovel){System.out.println("Cadastrando automóvel...");}

    public void editar(int id, Automovel automovel){System.out.println("Editando automóvel...");}

    public void excluir(int id){System.out.println("Excluindo automóvel...");}

}

class Servico{

    int id;
    String nome;
    double valor;

    public Servico(int id, String nome, double valor){

        setId(id);
        setNome(nome);
        setValor(valor);
        
    }

    public int getId(){return id;}
    public void setId(int id){
        if (this.id < 0) {System.out.println("Opção inválida");}
        else this.id = id;
    }

    public String getNome(){return nome;}
    public void setNome(String nome){
        if (this.nome == null || this.nome == " ") {System.out.println("Opção inválida");}
        else this.nome = nome;
    }
    
    public double getValor(){return valor;}
    public void setValor(double valor){
        if (this.valor < 0) {System.out.println("Opção inválida");}
        else this.valor = valor;
    }

    public void buscar(String input){System.out.println("Buscando serviço...");}

    public void cadastrar(Servico servico){System.out.println("Cadastrando serviço...");}

    public void editar(int id, Servico servico){System.out.println("Editando serviço...");}

    public void excluir(int id){System.out.println("Excluindo serviço...");}

}

class Orcamento{

    int id;
    int[] servicosId, pecasId;
    String automovelPlaca;
    double valor;

    public Orcamento(int id, int[] servicosId, int[] pecasId, String automovelPlaca, double valor){

        setId(id);
        setServicosId(servicosId);
        setPecasId(pecasId);
        setAutomovelPlaca(automovelPlaca);
        setValor(valor);

    }

    public int getId(){return id;}
    public void setId(int id){
        if (this.id <= 0) {System.out.println("Opção inválida");}
        else this.id = id;
    }

    public int[] getServicosId(){return servicosId;}
    public void setServicosId(int[] servicosId){
        if (this.servicosId == null) {System.out.println("Opção inválida");}
        else this.servicosId = servicosId;
    }

    public int[] getPecasId(){return pecasId;}
    public void setPecasId(int[] pecasId){
        if (this.pecasId == null) {System.out.println("Opção inválida");}
        else this.pecasId = pecasId;
    }

    public String getAutomovePlaca(){return automovelPlaca;}
    public void setAutomovelPlaca(String automovelPlaca){
        if (this.automovelPlaca == null || this.automovelPlaca == " ") {System.out.println("Opção inválida");}
        else this.automovelPlaca = automovelPlaca;
    }

    public double getValor(){return valor;}
    public void setValor(double valor){
        if (this.valor < 0) {System.out.println("Opção inválida");}
        else this.valor = valor;
    }

    public void buscar(String input){System.out.println("Buscando orçamento...");}

    public void cadastrar(Servico servico){System.out.println("Cadastrando orçamento...");}

    public void editar(int id, Servico servico){System.out.println("Editando orçamento...");}

    public void excluir(int id){System.out.println("Excluindo orçamento...");}

    public void finalizar(int id){System.out.println("Finalizando orçamento...");}

    public void registrarPagamento(double valor){System.out.println("Pagamento realizado!");}
    
}

class Funcionario{

    int id, nivel;
    String nome, CPF;

    public Funcionario(int id, String nome, String CPF, int nivel){

        setId(id);
        setNome(nome);
        setCPF(CPF);
        setNivel(nivel);
    }

    public int getId(){return id;}
    public void setId(int id){
        if (this.id <= 0) {System.out.println("Opção inválida");}
        else this.id = id;
    }

    public String getNome(){return nome;}
    public void setNome(String nome){
        if (this.nome == null || this.nome == " ") {System.out.println("Opção inválida");}
        else this.nome = nome;
    }

    public String getCPF(){return CPF;}
    public void setCPF(String CPF){
        if (this.CPF == null || this.CPF == " ") {System.out.println("Opção inválida");}
        else this.CPF = CPF;
    }

    public int getNivel(){return nivel;}
    public void setNivel(int nivel){
        if (this.nivel <= 0) {System.out.println("Opção inválida");}
        else this.nivel = nivel;
    }

    public void buscar(String input){System.out.println("Buscando orçamento...");}

    public void cadastrar(Funcionario funcionario){System.out.println("Cadastrando orçamento...");}

    public void editar(int id, Funcionario funcionario){System.out.println("Editando orçamento...");}

    public void excluir(int id){System.out.println("Excluindo orçamento...");}

}

class Main
{
    public static void main(String[] args) {
        
    }
}
