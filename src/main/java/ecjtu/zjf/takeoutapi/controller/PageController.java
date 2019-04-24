package ecjtu.zjf.takeoutapi.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ecjtu.zjf.takeoutapi.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping(value = {"/","/index"})
    public String index(){
        return "index";
    }
    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/goods")
    public String goods(){
        return  "goods";
    }
    @RequestMapping("/info")
    public String info(){
        return  "info";
    }
    @RequestMapping("/orders")
    public String orders(){
        return  "orders";
    }
    @RequestMapping(value = {"/goodsDetail","/goodsDetail/{id}"})
    public String goodForm(@PathVariable(value = "id",required = false) Integer id, Model model){
        model.addAttribute("dataStr",id );
        return  "goodFrom";
    }
}
