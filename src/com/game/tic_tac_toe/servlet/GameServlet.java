package com.game.tic_tac_toe.servlet;

import com.game.tic_tac_toe.database.GameDAO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.game.tic_tac_toe.servlet.Players.*;

/**
 * Servlet implementation class GameServlet
 */
@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GameDAO gameDAO;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init() {
        gameDAO = new GameDAO();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String rw = request.getParameter("row");
        String cl = request.getParameter("col");
        String ts = request.getParameter("tableSize");
        String cp = request.getParameter("currentPlayer");


        int row, col, currentPlayer, status, tableSize = 0;

        row = Integer.parseInt(rw);
        col = Integer.parseInt(cl);
        currentPlayer = Integer.parseInt(cp);
        tableSize = Integer.parseInt(ts);
        System.out.print("\nTable size : " + tableSize);
        Players game = new Players();

        game.createArray(tableSize);
        game.movePlayer(currentPlayer, row, col, tableSize);
        status = game.gameStatus(currentPlayer, row-1, col-1, tableSize);

        try {
            if (status == USER1_WON) {
                gameDAO.savingGameResults("USER1");
            } else if (status == USER2_WON) {
                gameDAO.savingGameResults("USER2");
            } else if (status == DRAW) {
                gameDAO.savingGameResults("DRAW");
            } else {

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        request.setAttribute("currentPlayer", (currentPlayer == Players.USER1) ? Players.USER2 : Players.USER1);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/main.jsp").forward(request, response);

    }
    

}
