package sep.pcc.rest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import sep.pcc.rest.dao.GenericDao;
import sep.pcc.rest.model.User;

@Service("userService")
@Transactional
public class UserService extends GenericServiceImpl<User> {
	
}
