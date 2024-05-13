package cue.edu.co.velocerentals.servlets;

import cue.edu.co.velocerentals.enums.VehicleStatus;
import cue.edu.co.velocerentals.enums.VehicleType;
import cue.edu.co.velocerentals.models.Vehicles;
import cue.edu.co.velocerentals.service.Impl.VehiclesServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/vehicleFilter")
public class LoadVehicles extends HttpServlet {

    @Inject
    private VehiclesServiceImpl vehiclesService;

    private static final Logger LOGGER = Logger.getLogger(LoadVehicles.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filter = req.getParameter("filter");
        String type = req.getParameter("type");
        String status = req.getParameter("status");

        List<Vehicles> vehiclesList;

        if (filter != null) {
            switch (filter) {
                case "price_asc":
                    vehiclesList = vehiclesService.listVehiclesPriceAsc();
                    break;
                case "price_desc":
                    vehiclesList = vehiclesService.listVehiclesPriceDesc();
                    break;
                case "type":
                    VehicleType vehicleType = VehicleType.fromString(type);
                    vehiclesList = vehiclesService.listVehiclesType(vehicleType);
                    break;
                case "status":
                    VehicleStatus vehicleStatus = VehicleStatus.fromString(status);
                    vehiclesList = vehiclesService.listVehiclesStatus(vehicleStatus);
                    break;
                default:
                    vehiclesList = vehiclesService.listVehicles();
                    break;
            }
            req.setAttribute("vehiclesList", vehiclesList);
        } else {
            vehiclesList = vehiclesService.listVehicles();
        }

        req.setAttribute("vehiclesListMain", vehiclesList);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
