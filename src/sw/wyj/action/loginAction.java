package sw.wyj.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import sw.wyj.vo.User;

import javax.servlet.http.HttpSession;

/**
 * Created by 王一疆 on 2017/9/19
 */
public class loginAction extends ActionSupport implements ModelDriven<User>{
    public User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    @Override
    public void validate() {
        if(user.username.equals("")||user.username.trim().equals("")){
            this.addFieldError("username","用户名不能为空");
        }
        if(user.psw.equals("")||user.psw.trim().equals("")){
            this.addFieldError("psw","密码不能为空");
        }
        HttpSession session = ServletActionContext.getRequest().getSession();

        String checkCode_ = (String)session.getAttribute("checkCode");
        if(user.checkcode.equals("")||user.checkcode.trim().equals("")){
            this.addFieldError("check","验证码不能为空");
        }else if(!user.checkcode.equals(checkCode_))
        {
            this.addFieldError("checkcode","验证码错误");
        }
        System.out.print(checkCode_);
    }


    public String login() {
        if(user.username.equals("admin")&&user.psw.equals("admin")&&user.type.equals("普通用户")){
            ActionContext.getContext().getSession().put("username",user.username);
            return SUCCESS;

        }else if(user.username.equals("admin")&&user.psw.equals("admin")&&user.type.equals("管理员")){
            return "successAdmin";
        }else {
            return ERROR;
        }

    }



}
