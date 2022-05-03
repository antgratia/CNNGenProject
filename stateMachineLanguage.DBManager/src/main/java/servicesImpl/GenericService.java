package servicesImpl;

import java.sql.SQLException;

import org.neo4j.ogm.session.Session;

import domain.DBConnFactory;
import domain.Layer;
import services.IService;

abstract class GenericService<T extends Layer> implements IService<T> {

    private static final int DEPTH_LIST = 0;
    private static final int DEPTH_ENTITY = 1;
	private Session session;
    
	public GenericService() {
		try {
			this.session = DBConnFactory.getInstance().getSession();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public Iterable<T> findAll() {
        return session.loadAll(getEntityType(), DEPTH_LIST);
    }


    public T find(Long id) {
        return session.load(getEntityType(), id, DEPTH_ENTITY);
    }


    public void delete(Long id) {
        session.delete(session.load(getEntityType(), id));
    }


    public T createOrUpdate(T entity) {
        session.save(entity, DEPTH_ENTITY);
        return find(entity.getId());
    }

    abstract Class<T> getEntityType();
}
