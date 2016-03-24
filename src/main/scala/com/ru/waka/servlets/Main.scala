package com.ru.waka.servlets

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class Main extends HttpServlet {
  override def doGet(request: HttpServletRequest, response: HttpServletResponse) {
    response.setContentType("text/plain")
    response.getWriter.println("Hello, world")
  }
}
