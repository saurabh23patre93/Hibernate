package com.nt.dao;

public interface ManyToOneDao {
	public void saveDataUsingChild();
	public void loadDataUsingChild();
	public void deleteAllChildsAndItsParent();
	public void deleteOneChildFromParent();
}
