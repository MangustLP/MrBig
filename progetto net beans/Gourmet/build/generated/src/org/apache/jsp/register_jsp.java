package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("\t<head>\n");
      out.write("\t\t<title>Gourmet - Register a new account</title>\n");
      out.write("\t\t<meta charset=\"UTF-8\">\n");
      out.write("\t\t<meta name=\"author\" content=\"Mr. Big\">\n");
      out.write("\t\t<meta name=\"description\" content=\"Register a new account\">\n");
      out.write("\t\t<link rel=\"stylesheet\" href=\"css/register.css\">\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("\t<body background=\"img/wine.jpg\">\n");
      out.write("\t\t<div class=\"signup-box\">\n");
      out.write("\t\t\t<p>Create a new account</p>\n");
      out.write("\t\t\t<form class=\"createaccount-form\" name=\"createaccount\" action=\"\" method=\"post\">\n");
      out.write("\t\t\t  \t<input type=\"text\" value=\"\" name=\"FirstName\" id=\"FirstName\" spellcheck=\"false\" placeholder=\"Nome\">\n");
      out.write("\t\t\t  \t<input type=\"text\" value=\"\" name=\"LastName\" id=\"LastName\" spellcheck=\"false\" placeholder=\"Cognome\">\n");
      out.write("\t\t\t  \t<input type=\"text\" value=\"\" maxlength=\"30\" autocomplete=\"off\" name=\"UserName\" id=\"UserName\" spellcheck=\"false\" placeholder=\"Username\">\n");
      out.write("\t\t\t  \t<input type=\"mail\" value=\"\" maxlength=\"30\" autocomplete=\"off\" name=\"Email\" id=\"Email\" spellcheck=\"false\" placeholder=\"Email@address\">\n");
      out.write("\t\t\t  \t<input type=\"password\" name=\"Passwd\" id=\"Passwd\" autocomplete=\"off\" placeholder=\"Password\">\n");
      out.write("\t\t\t  \t<input type=\"password\" name=\"PasswdAgain\" id=\"PasswdAgain\" autocomplete=\"off\" placeholder=\"Retype Password\">\n");
      out.write("\t\t\t  \t<input type=\"submit\" value=\"Register me now!\" id=\"submit-button\">\n");
      out.write("\t\t\t</form>\n");
      out.write("\t\t</div>\n");
      out.write("\t</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
