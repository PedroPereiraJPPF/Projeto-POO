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

class Main
{
    public static void main(String[] args) {
        
    }
}