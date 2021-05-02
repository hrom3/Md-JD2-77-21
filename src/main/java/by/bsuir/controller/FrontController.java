package by.bsuir.controller;

import by.bsuir.domain.Dealer;
import by.bsuir.domain.User;
import by.bsuir.repository.IDealerRepository;
import by.bsuir.repository.IUserRepository;
import by.bsuir.repository.impl.DealerRepositoryImpl;
import by.bsuir.repository.impl.UserRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class FrontController extends HttpServlet {

    public IUserRepository iUserRepository
            = new UserRepositoryImpl();

    public IDealerRepository iDealerRepository
            = new DealerRepositoryImpl();

    public FrontController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
 //       System.out.println("I'm here");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/hello");
        if (dispatcher != null) {
            System.out.println("Forward will be done!");

            req.setAttribute("userName", iUserRepository
                            .findAll()
                            .stream()
                            .map(User::getName)
                            .collect(Collectors.joining(", ")));

            req.setAttribute("dealerName", iDealerRepository
                    .findAll()
                    .stream()
                    .map(Dealer::getName)
                    .collect(Collectors.joining(", ")));

            dispatcher.forward(req, resp);
        }
    }
}
