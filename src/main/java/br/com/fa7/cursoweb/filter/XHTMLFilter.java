package br.com.fa7.cursoweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.fa7.cursoweb.dao.PageDao;

@WebFilter("*.xhtml")
public class XHTMLFilter implements Filter {

	private PageDao pageDao;

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		initApplicationScoped(request);
		String page = getPage(request);
		if (pageDao.existsPage(page)) {
			pageDao.incrementAccess(page);
		}
		chain.doFilter(request, response);
	}

	private String getPage(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String[] a = uri.split("/");
		return a[a.length - 1];
	}

	private void initApplicationScoped(ServletRequest request) {
		ServletContext context = request.getServletContext();
		this.pageDao = (PageDao) context.getAttribute("pageDao");
	}

	public void destroy() {
	}
}