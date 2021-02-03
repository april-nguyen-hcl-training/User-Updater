package com.hcl.userupdater.controller;

import com.hcl.userupdater.domain.User;
import com.hcl.userupdater.repository.UserDAO;
import com.hcl.userupdater.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class UserController {

  private static final Logger logger = Logger.getLogger(UserController.class);

  private UserService userService;

  @Autowired(required=true)
  @Qualifier(value="userService")
  public void setPersonService(UserService us){
    this.userService = us;
  }

  @RequestMapping(value = {"/edit/", "/edit/{id}"}, params = "id", method = RequestMethod.GET)
  public String editUser(@RequestParam("id") long id, ModelMap model, RedirectAttributes attributes){
    User user = this.userService.getUserById(id);
    if(user != null) {
      logger.info("Found user to edit" + user);
      model.addAttribute("user", this.userService.getUserById(id));
      return "edit";
    } else {
      logger.info("Invalid user id: " + id);
      attributes.addFlashAttribute("id", id);
      attributes.addFlashAttribute("idAlert", "Invalid User Id");
      return "redirect:/users";
    }
  }

  @RequestMapping(value= "/user/update", method = RequestMethod.POST )
  public String updateUser(@RequestParam long id,
                                 @RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam String birthday,
                                 ModelMap model,
                                 RedirectAttributes attributes) {
    Date bDay = null;
    User user = userService.getUserById(id);
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      bDay = format.parse(birthday);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    User u = new User(id, username, password, firstName, lastName, email, bDay, user.getDateCreated());

    logger.info("Created User from edit form input: " + u);

    boolean validInput = true;
    /* Verify that
     * username (if changed) is not taken
     * username length >= 3 and username can only contain letters, numbers, @, -, $, and _
     * password longer than 8 char and contains a number and a letter
     * birthday is not greater than today & 18 <= age <= 130 (using birthday)
     */
    if(!user.getUsername().equals(username) && userService.listUsers().stream().anyMatch(us -> us.getUsername().equals(username))) {
      model.addAttribute("usernameAlert", "Username is taken!");
      validInput = false;
    }
    Pattern validUsername = Pattern.compile("[0-9a-zA-Z@_$-]{3,20}");
    Pattern validPassword = Pattern.compile("^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])*.{8,}$");
    if(!validUsername.matcher(u.getUsername()).matches()) {
      model.addAttribute("usernameAlert",
        "Username must be 3-20 characters long, and can only contain letters, numbers, @, -, $, and _");
      validInput = false;
    }
    if(!validPassword.matcher(u.getPassword()).matches()) {
      model.addAttribute("passwordAlert",
        "Password must be at least 8 characters, and contain a letter and a number!");
      validInput = false;
    }
    Calendar now = Calendar.getInstance();
    now.add(Calendar.YEAR, -18);
    Date eighteenYearsAgo = now.getTime();
    if(eighteenYearsAgo.before(u.getBirthday())) {
      model.addAttribute("birthdayAlert",
        "You must be 18 or older!");
      validInput = false;
    }
    now = Calendar.getInstance();
    now.add(Calendar.YEAR, -130);
    Date oneHundredThirtyYearsAgo = now.getTime();
    if(oneHundredThirtyYearsAgo.after(u.getBirthday())) {
      model.addAttribute("birthdayAlert",
        "Invalid Birthday! Are you really 130 or older?");
      validInput = false;
    }

    if(validInput) {
      logger.info("Input for User is valid. Trying to update user...");
      this.userService.updateUser(u);
      attributes.addFlashAttribute("successAlert", "User #" + u.getId() + " updated successfully!");
      return "redirect:/users";
    } else {
      logger.info("Input for User is invalid. Returning invalid user...");
      model.addAttribute("user", u);
      return "edit";
    }
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String listUsers(Model model) {
    model.addAttribute("user", new User());
    model.addAttribute("listUsers", this.userService.listUsers());
    return "user";
  }

  @RequestMapping("/remove/{id}")
  public String removeUser(@PathVariable("id") int id, RedirectAttributes attributes){
    User user = this.userService.getUserById(id);
    if(user != null) {
      logger.info("Found user to remove: " + user);
      this.userService.removeUser(id);
      attributes.addFlashAttribute("successAlert", "User #" + id + " removed successfully!");
    } else {
      logger.info("Invalid user id: " + id);
      attributes.addFlashAttribute("failAlert", "Failed to remove user! Invalid User Id: " + id);
    }
    return "redirect:/users";
  }
}
