package dao;

import com.roroldo.ishare.dao.UserDao;
import com.roroldo.ishare.dao.impl.UserDaoImpl;
import com.roroldo.ishare.domain.User;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class UserDaoImplTest {
    private UserDao dao = new UserDaoImpl();

    @Test
    public void testFindByUsername() {
        System.out.println("user = " + dao.findByUsername("root"));
    }

    @Test
    public void testGetTime() {
        LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:ss:mm"));
        System.out.println(format);
    }


}
