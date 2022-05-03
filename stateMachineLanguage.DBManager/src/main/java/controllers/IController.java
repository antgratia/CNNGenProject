package controllers;

public interface IController<T> {

	Iterable<T> findAll();

	T find(Long id);

	void delete(Long id);

	T createOrUpdate(T object);
	
	T findByLayerpos(int layerpos);
}
