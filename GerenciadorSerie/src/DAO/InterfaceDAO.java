/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author lab1
 */
//interface java: definir um padrão para seguir
// interface: contrato de programação estabelecido entre programadores
public interface InterfaceDAO {
    //todos os metodos aqui sao abstratos. Todos eles lançam uma exceção, quem for implementar, vai ter que tratar as exceções
    
    public abstract Connection conectar() throws Exception;
    public abstract void incluir(Object o) throws Exception;
    public abstract void editar(Object o) throws Exception;
    public abstract void excluir(Object o) throws Exception;
    public abstract Object pesquisar(int id) throws Exception;
    public abstract List<Object> listar() throws Exception;
}
