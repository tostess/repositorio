/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Util.HibernateUtil;
import java.sql.Connection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 *
 * @author lab1
 */
//interface java: definir um padrão para seguir
// interface: contrato de programação estabelecido entre programadores
public abstract class HibernateDAO<T> {

    private Session session;
    private Class<T> persistClass;

    //todos os metodos aqui sao abstratos. Todos eles lançam uma exceção, quem for implementar, vai ter que tratar as exceções
    public HibernateDAO(Class<T> persistClass) {
        this.persistClass = persistClass;
        session = HibernateUtil.getSession();
    }
    
    public Criteria criarCriteria() {
        return session.createCriteria(persistClass);
    }
    
    public T pesquisar(int id) {
        return (T) session.get(persistClass, id);
    }
    
    public void excluir(T t) {
        session.delete(t);
    }
    
    public void incluir(T o) {
        session.save(o);
    }
    
    public void editar(T o) {
        session.update(o);
    }
    
    public abstract List<T> listar() throws Exception;
}
