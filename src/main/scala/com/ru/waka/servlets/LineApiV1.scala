package com.ru.waka.servlets

import javax.servlet.http.{HttpServlet, HttpServletRequest, HttpServletResponse}

class LineApiV1 extends HttpServlet  {

  override def doPost(request: HttpServletRequest, response: HttpServletResponse) {
    response.setStatus(200)
    response.setContentType("application/json;charset=UTF-8")
    response.getWriter.println("{}")
  }
}

