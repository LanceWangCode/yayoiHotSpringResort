package com.login.model;

import java.util.*;

public interface LoginDAO_interface {

	LoginVO selectByUsernameAndPassowrd(String username, String password);

}
