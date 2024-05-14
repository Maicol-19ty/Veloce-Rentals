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

    // Method to handle GET requests
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Retrieving filter parameters from the request
        String filter = req.getParameter("filter");
        String type = req.getParameter("type");
        String status = req.getParameter("status");

        List<Vehicles> vehiclesList;

        // Checking if a filter is applied and processing accordingly
        if (filter != null) {
            switch (filter) {
                case "price_asc":
                    // Sorting vehicles by price in ascending order
                    vehiclesList = vehiclesService.listVehiclesPriceAsc();
                    break;
                case "price_desc":
                    // Sorting vehicles by price in descending order
                    vehiclesList = vehiclesService.listVehiclesPriceDesc();
                    break;
                case "type":
                    // Filtering vehicles by type
                    VehicleType vehicleType = VehicleType.fromString(type);
                    vehiclesList = vehiclesService.listVehiclesType(vehicleType);
                    break;
                case "status":
                    // Filtering vehicles by status
                    VehicleStatus vehicleStatus = VehicleStatus.fromString(status);
                    vehiclesList = vehiclesService.listVehiclesStatus(vehicleStatus);
                    break;
                default:
                    // Default case: list all vehicles
                    vehiclesList = vehiclesService.listVehicles();
                    break;
            }
            req.setAttribute("vehiclesList", vehiclesList);
        } else {
            // If no filter is applied, list all vehicles
            vehiclesList = vehiclesService.listVehicles();
        }

        // Setting attributes and forwarding the request to the main.jsp page
        req.setAttribute("vehiclesListMain", vehiclesList);
        req.getRequestDispatcher("main.jsp").forward(req, resp);
    }
}
