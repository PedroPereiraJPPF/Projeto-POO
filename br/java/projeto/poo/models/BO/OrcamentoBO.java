package br.java.projeto.poo.models.BO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import br.java.projeto.poo.DAO.OrcamentoDao;
import br.java.projeto.poo.models.VO.OrcamentoVO;
import br.java.projeto.poo.models.VO.PecaVo;
import br.java.projeto.poo.models.VO.ServicoVO;
public class OrcamentoBO {
    OrcamentoDao orcamentoDao = new OrcamentoDao();

    public ArrayList<OrcamentoVO> listar() throws Exception {
        try {
            ResultSet orcamentos = orcamentoDao.listar();
            ArrayList<OrcamentoVO> listarOrcamentos = new ArrayList<OrcamentoVO>();
            while(orcamentos.next()) {
                OrcamentoVO orcamento = new OrcamentoVO();
                orcamento.setId(orcamentos.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentos.getString("placaVeiculo"));
                orcamento.setValor(orcamentos.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentos.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentos.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentos.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentos.getString("cpfResponsavel"));
                listarOrcamentos.add(orcamento);
            }

            return listarOrcamentos;

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean inserir(OrcamentoVO vo) throws Exception {
        try {
            return this.orcamentoDao.inserir(vo); 
        } catch (Exception e) {
            throw e;
        }
    }

    public OrcamentoVO atualizar(OrcamentoVO VO) throws Exception {
        try {
           return orcamentoDao.atualizar(VO);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean deletar(long id) throws Exception {
        try {
            OrcamentoVO vo = new OrcamentoVO(); 
            vo.setId(id);
            return orcamentoDao.deletar(vo);
        } catch (Exception e) {
            throw e;
        }
    }

    public OrcamentoVO buscarPorId(long id) throws Exception {
        try {
            OrcamentoVO orcamentoVO = new OrcamentoVO();
            orcamentoVO.setId(id);
            ResultSet orcamentoBuscado = orcamentoDao.buscarPorId(orcamentoVO);
            ResultSet pecasBuscadas, servicosBuscados;
            ArrayList<PecaVo> pecas = new ArrayList<>();
            ArrayList<ServicoVO> servicos = new ArrayList<>();
            if(orcamentoBuscado.next()) {
                orcamentoVO.setId(orcamentoBuscado.getLong("id"));
                orcamentoVO.setCpfCliente(orcamentoBuscado.getString("cpfCliente"));
                orcamentoVO.setCpfFuncionario(orcamentoBuscado.getString("cpfResponsavel"));
                orcamentoVO.setPlacaVeiculo(orcamentoBuscado.getString("placaVeiculo"));
                orcamentoVO.setValor(orcamentoBuscado.getDouble("valor"));
                pecasBuscadas = orcamentoDao.listarPecas(orcamentoVO);
                servicosBuscados = orcamentoDao.listarServicos(orcamentoVO);

                while(pecasBuscadas.next()) {
                    PecaVo peca = new PecaVo(pecasBuscadas.getLong("id"),
                    pecasBuscadas.getString("nome"),
                    pecasBuscadas.getString("fabricante"),
                    pecasBuscadas.getDouble("preco"),
                    0);
                    peca.setQuantidade(orcamentoDao.contarPecas(peca, id));
                    pecas.add(peca);
                }

                while(servicosBuscados.next()) {
                    servicos.add(new ServicoVO(servicosBuscados.getLong("id"),
                    servicosBuscados.getString("nome"),
                    servicosBuscados.getDouble("preco")));
                }

                orcamentoVO.setPecas(pecas);
                orcamentoVO.setServicos(servicos);
            }
            return orcamentoVO;
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<OrcamentoVO> buscarPorVeiculo(String placa) throws Exception {
        try {
            OrcamentoVO orcamentoVO = new OrcamentoVO();
            orcamentoVO.setPlacaVeiculo(placa);
            ResultSet orcamentosBuscados = orcamentoDao.buscarPorVeiculo(orcamentoVO);
            ArrayList<OrcamentoVO> orcamentos = new ArrayList<>();
            while(orcamentosBuscados.next()) {
                OrcamentoVO orcamento = new OrcamentoVO();
                orcamento.setId(orcamentosBuscados.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentosBuscados.getString("placaVeiculo"));
                orcamento.setValor(orcamentosBuscados.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentosBuscados.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentosBuscados.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentosBuscados.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentosBuscados.getString("cpfResponsavel"));
                orcamentos.add(orcamento);
            }

            return orcamentos;
        } catch (Exception e) {
            throw new Exception("erro ao buscar orcamentos");
        }
    }

    public ArrayList<OrcamentoVO> buscarPorCPFCliente(String cpf) throws Exception {
        try {
            OrcamentoVO orcamentoVO = new OrcamentoVO();
            orcamentoVO.setCpfCliente(cpf);
            ResultSet orcamentosBuscados = orcamentoDao.buscarPorCPFCliente(orcamentoVO);
            ArrayList<OrcamentoVO> orcamentos = new ArrayList<>();
            while(orcamentosBuscados.next()) {
                OrcamentoVO orcamento = new OrcamentoVO();
                orcamento.setId(orcamentosBuscados.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentosBuscados.getString("placaVeiculo"));
                orcamento.setValor(orcamentosBuscados.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentosBuscados.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentosBuscados.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentosBuscados.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentosBuscados.getString("cpfResponsavel"));
                orcamentos.add(orcamento);
            }

            return orcamentos;
        } catch (Exception e) {
            throw new Exception("erro ao buscar orcamentos");
        }
    }

    public ArrayList<OrcamentoVO> buscarPorData(String data) throws Exception {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dataGerada = dateFormat.parse(data);
            java.sql.Date sqlDate = new java.sql.Date(dataGerada.getTime());

            OrcamentoVO orcamentoVO = new OrcamentoVO();
            orcamentoVO.setDataDeCriação(sqlDate);
            ResultSet orcamentosBuscados = orcamentoDao.buscarPorData(orcamentoVO);
            ArrayList<OrcamentoVO> orcamentos = new ArrayList<>();
            while(orcamentosBuscados.next()) {
                OrcamentoVO orcamento = new OrcamentoVO();
                orcamento.setId(orcamentosBuscados.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentosBuscados.getString("placaVeiculo"));
                orcamento.setValor(orcamentosBuscados.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentosBuscados.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentosBuscados.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentosBuscados.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentosBuscados.getString("cpfResponsavel"));
                orcamentos.add(orcamento);
            }

            return orcamentos;
        } catch (Exception e) {
            throw new Exception("erro ao buscar orcamentos");
        }
    }
    
    public ArrayList<OrcamentoVO> buscarPorStatusData(OrcamentoVO vo) throws Exception {
        try {
            ResultSet orcamentosBuscados = orcamentoDao.buscarPorStatusData(vo);
            ArrayList<OrcamentoVO> orcamentos = new ArrayList<>();
            while(orcamentosBuscados.next()) {
                OrcamentoVO orcamento = new OrcamentoVO();
                orcamento.setId(orcamentosBuscados.getLong("id"));
                orcamento.setPlacaVeiculo(orcamentosBuscados.getString("placaVeiculo"));
                orcamento.setValor(orcamentosBuscados.getDouble("valor"));
                orcamento.setDataDeCriação(orcamentosBuscados.getDate("dataDeCriacao"));
                orcamento.setDataDeEncerramento(orcamentosBuscados.getDate("dataDeEncerramento"));
                orcamento.setCpfCliente(orcamentosBuscados.getString("cpfCliente"));
                orcamento.setCpfFuncionario(orcamentosBuscados.getString("cpfResponsavel"));
                orcamentos.add(orcamento);
            }

            return orcamentos;
        } catch (Exception e) {
            throw new Exception("erro ao buscar orcamentos");
        }
    }

    public Boolean encerrarRelatorio(OrcamentoVO vo) throws Exception {
        try {
            return orcamentoDao.encerrarRelatorio(vo);
        } catch (Exception e) {
            throw new Exception("Erro ao encerrar orcamento");
        }
    }
}
