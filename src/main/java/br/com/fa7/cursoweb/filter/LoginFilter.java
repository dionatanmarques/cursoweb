package br.com.fa7.cursoweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fa7.cursoweb.bean.UsuarioLogadoBean;

@WebFilter("/pages/*")
public class LoginFilter implements Filter {

	private UsuarioLogadoBean usuarioLogado;
	
	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		initSession(req);
		if (usuarioLogado != null && usuarioLogado.isLogged()) {
			chain.doFilter(request, response);
		} else {
			redirectToLogin(res, req);
		}
	}

	private void redirectToLogin(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.sendRedirect(request.getContextPath() + "/login.xhtml");
	}

	private void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			this.usuarioLogado = (UsuarioLogadoBean) session.getAttribute("usuarioLogadoBean");
		}
	}

	public void destroy() {
	}
}