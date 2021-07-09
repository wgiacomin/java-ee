package com.ufpr.tads.web2.facade;

import com.ufpr.tads.web2.beans.ProdutoCategoriaBean;
import com.ufpr.tads.web2.dao.ProdutoCategoriaDAO;
import com.ufpr.tads.web2.dao.utils.ConnectionFactory;
import com.ufpr.tads.web2.exceptions.BeanInvalidoException;
import com.ufpr.tads.web2.exceptions.DAOException;
import com.ufpr.tads.web2.exceptions.FacadeException;
import com.ufpr.tads.web2.exceptions.RegistroDuplicadoException;
import com.ufpr.tads.web2.exceptions.RegistroInexistenteException;
import java.util.List;

public class CategoriaProdutoFacade {

    public static ProdutoCategoriaBean buscar(ProdutoCategoriaBean produtoCategoria) throws FacadeException, BeanInvalidoException, RegistroInexistenteException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoCategoriaDAO bd = new ProdutoCategoriaDAO(factory.getConnection());
            produtoCategoria = bd.buscar(produtoCategoria);

            if (produtoCategoria == null) {
                throw new RegistroInexistenteException();
            }

            return produtoCategoria;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar categoria de produto " + produtoCategoria.getId(), e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static List<ProdutoCategoriaBean> buscarTodos() throws FacadeException, BeanInvalidoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoCategoriaDAO bd = new ProdutoCategoriaDAO(factory.getConnection());
            List<ProdutoCategoriaBean> produtoCategoria = bd.buscarTodos();
            return produtoCategoria;

        } catch (DAOException e) {
            throw new FacadeException("Erro ao buscar todos as categorias de produto: ", e);

        } catch (NullPointerException e) {
            throw new BeanInvalidoException();

        }
    }

    public static void Inserir(ProdutoCategoriaBean produtoCategoria) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {

            ProdutoCategoriaDAO bd = new ProdutoCategoriaDAO(factory.getConnection());
            produtoCategoria = bd.buscarPorNome(produtoCategoria);

            if (produtoCategoria != null) {
                throw new RegistroDuplicadoException();
            }

            bd.inserir(produtoCategoria);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao inserir categoria de produto: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

    public static void Remover(ProdutoCategoriaBean produtoCategoria) throws FacadeException, BeanInvalidoException, RegistroDuplicadoException {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            ProdutoCategoriaDAO bd = new ProdutoCategoriaDAO(factory.getConnection());

//            TODO: atendimento check
//            CadastroDAO cbd = new CadastroDAO(factory.getConnection());
//            
//            int registros = cbd.buscarPorProdutoCategoria(produtoCategoria);
//            if (registros > 0) {
//                throw new CadastrosComProdutoCategoriaException(registros);
//            }
            bd.remover(produtoCategoria);
        } catch (DAOException e) {
            throw new FacadeException("Erro ao deletar categoria de produto: ", e);
        } catch (NullPointerException e) {
            throw new BeanInvalidoException();
        }
    }

}
