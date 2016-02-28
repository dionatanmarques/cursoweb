package br.com.fa7.cursoweb.dao;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(eager = true)
@ApplicationScoped
public class PageDao {

	private HashMap<String, Integer> pages = new HashMap<String, Integer>();

	public PageDao() {
		insertPages();
	}

	private void insertPages() {
		pages.put("jogovelha.xhtml", 0);
		pages.put("usuarios.xhtml", 0);
		pages.put("index.xhtml", 0);
	}

	public boolean existsPage(String page) {
		if (pages.get(page) != null) {
			return true;
		}
		return false;
	}

	public void incrementAccess(String page) {
		Integer amount = pages.get(page);
		if (amount != null) {
			amount++;
			pages.put(page, amount);
		}
	}

	public HashMap<String, Integer> getPages() {
		return pages;
	}
}