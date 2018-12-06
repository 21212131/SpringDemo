package com.zzc.demo;

import com.zzc.demo.dao.GameDao;
import com.zzc.demo.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Model (模型): model为service dao entity 数据库及所有处理应用程序数据逻辑的部分 。通常模型对象负责在数据库中存取数据
//View（视图）是应用程序中处理数据显示的部分。通常视图是依据模型数据创建的。输出数据并允许用户操纵的方式.。
//Controller（控制器）是应用程序中处理用户交互的部分。通常控制器负责从视图读取数据，控制用户输入，并向模型发送数据。
//控制器接受用户的输入并调用模型和视图去完成用户的需求，所以当单击Web页面中的超链接和发送HTML表单时，控制器本身不输出任何东西和做任何处理。
// 它只是接收请求并决定调用哪个模型构件去处理请求，然后再确定用哪个视图来显示返回的数据。


//@RestController
@Controller
@RequestMapping
@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    @Autowired
    private GameDao gameDao;

    //ModelAttribute runs before all methods under controller
    //it also helps pass model
    //in this case it pass an model "name:Zicong"
    @ModelAttribute("name")
    public String preRun() {
        System.out.println("Incoming request!");
        String name = "Zicong";
        return name;
    }


    @RequestMapping("/")
    public String index(){
        return "games";
    }


    //model here is actually able to send parameters to front end also
    @RequestMapping("/showgames/")
    public String showGames(ModelMap model){
        model.put("city","San Diego");
        return "games";
    }


    @RequestMapping(value="/getAllGames", method = RequestMethod.GET)
    @ResponseBody
    public List<Game> getAllGames(){
        List<Game> games = gameDao.queryGame();
        return games;
    }




    @RequestMapping(value = "/addGame", method = RequestMethod.GET)
    @ResponseBody
    public void addGame(@RequestParam("gameID") String gameID,@RequestParam("gameName") String gameName,@RequestParam("gameType") String gameType){
        System.out.println(gameName);
        System.out.println(gameType);
        Game newGame = new Game(Integer.valueOf(gameID),gameName,gameType);
        gameDao.addGame(newGame);
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> test(@RequestBody Map<String,String> info){
        System.out.println(info);
        return info;
    }

    //PathVariable is used when there is only a url followed by one value that can be located directly
    //For this example, we assume "age" will be following directly after /test2/
    //So we can directly grab the path variable (literally variable on the path) and use it
    @RequestMapping(value = "/test2/{age}", method = RequestMethod.GET)
    @ResponseBody
    public void test2(@PathVariable("age") String age){
        System.out.println(age);
    }

    //处理局部异常
    @ExceptionHandler
    public ModelAndView exceptionHandler(Exception ex){
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex);
        System.out.println("Exception in \"error\""+ex.toString());
        return mv;
    }
    @RequestMapping("/tryError")
    public void error(){
        int i = 5/0;
    }
    //处理全局异常
    @ControllerAdvice
    public class testControllerAdvice {
        //内部需要各自定义ExceptionHandler
        //但如果在外部已被局部异常捕获，则不进行反应
        @ExceptionHandler
        public ModelAndView exceptionHandler(Exception ex){
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("exception", ex);
            System.out.println("发生错误:"+ex.toString());
            return mv;
        }
    }



//    @RequestMapping(value = "/TempIndex", method = RequestMethod.GET)
//    public ModelAndView index(HttpServletResponse response,
//                              HttpServletRequest request) throws MalformedURLException {
//        return new ModelAndView("Tempindex");
//    }





//    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
//    @ResponseBody
//    public Map<String,Object> ajax(@RequestParam("cityName") String city){
//        List<Game> games = gameDao.queryGame();
//        Map<String, Object> map=new HashMap<String, Object>();
//        System.out.println(games);
//        map.put("name", "Hi!");
//        map.put("city", "San Diego");
//        map.put("games",games);
//        System.out.println(city);
//        return map;
//    }


//    @RequestMapping(value = "/index", method = RequestMethod.GET)
//    public String index(ModelMap modelMap) {
//        modelMap.put("msg", "SpringBoot Ajax 示例");
//        return "index";
//    }

//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    @ResponseBody
//    public String home() {
//        return "home";
//    }

//    @RequestMapping(value = "/data", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Game> data() {
//        List<Game> list = gameDao.queryGame();
//        return list;
//    }





    //initiate the application through main here
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DemoApplication.class);
    }

}
