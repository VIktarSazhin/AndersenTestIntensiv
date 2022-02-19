package command;

import dao.UserDao;
import entity.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UpdateCommand implements Command{
    private final UserDao userDao;

    public UpdateCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        UserService userService = new UserServiceImpl(userDao);
        User existingUser = userService.selectUser(id);
        request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        //
        List<User> listUser = userService.selectAllUsers();
        request.setAttribute("listUser", listUser);
        request.getRequestDispatcher("user-list.jsp");
        return "/user-list.jsp";
    }
}
