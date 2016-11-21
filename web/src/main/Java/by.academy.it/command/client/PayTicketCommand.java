package by.academy.it.command.client;

import by.academy.it.command.ActionCommand;
import by.academy.it.command.ConfigurationManager;
import by.academy.it.entity.Flight;
import by.academy.it.entity.Ticket;
import by.academy.it.services.FlightServiceImpl;
import by.academy.it.services.TicketServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PayTicketCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        final Logger LOG = Logger.getLogger(PayTicketCommand.class);
        String page;

        HttpSession session = request.getSession(true);
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        TicketServiceImpl.getInstance().payTicket(ticketId);
        List<Flight> flights = FlightServiceImpl.getInstance().getAll();
        request.setAttribute("flights", flights);
        int userId = (Integer) session.getAttribute("userid");
        List<Ticket> tickets = TicketServiceImpl.getInstance().getAllByUser(userId);
        request.setAttribute("tickets", tickets);
        LOG.info("Ticket payed successfully");

        page = ConfigurationManager.getProperty("path.page.user");
        return page;
    }
}