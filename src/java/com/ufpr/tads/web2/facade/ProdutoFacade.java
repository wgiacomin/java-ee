package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.ProdutoBean;
import com.ufpr.tads.web2.dao.ProdutoDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.RegistroComUsoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import java.util.List;

public class ProdutoFacade {

    public static ProdutoBean buscar(ProdutoBean produto) throws FacadeException, BeanInvalidoException, RegistroInexistenteException  {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoDAO bd = new ProdutoDAO(factory.getConnection());
            produto = bd.buscar(produto);

            if (produto == null) {
                throw new RegistroInexistenteException();
            }
            return produto;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar produto " + produto.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<ProdutoBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoDAO bd = new ProdutoDAO(factory.getConnection());
            List<ProdutoBean> produto = bd.buscarTodos();
            return produto;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos os produtos: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }
    
     public static void Inserir(ProdutoBean produto) throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            
            ProdutoDAO bd = new ProdutoDAO(factory.getConnection());
            bd.inserir(produto);
            
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir produto: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void Remover(ProdutoBean produto) throws FacadeException, BeanInvalidoException, RegistroComUsoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoDAO bd = new ProdutoDAO(factory.getConnection());
            
//            TODO: Atendimento check
//            CadastroDAO cbd = new CadastroDAO(factory.getConnection());
//            
//            int registros = cbd.buscarPorProduto(produto);
//            if (registros > 0) {
//                throw new RegistroComUsoException(registros);
//            }
            
            bd.remover(produto);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar produto: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
