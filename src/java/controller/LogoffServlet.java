/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Enrico
 */
@WebServlet(name = "LogoffServlet", urlPatterns = {"/logoff"})
public class LogoffServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession s = req.getSession();

        try {
            s.invalidate();
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

}
