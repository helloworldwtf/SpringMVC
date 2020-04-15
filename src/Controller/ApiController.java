package Controller;
import jdbc.StudentHomeworkJdbc;
import jdbc.HomeworkJdbc;
import jdbc.StudentJdbc;
import model.Homework;
import model.Student;
import model.StudentHomework;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
@Controller
public class ApiController {
    ApplicationContext applicationContext0 = new AnnotationConfigApplicationContext(jdbc.StudentHomeworkJdbc.class);
    StudentHomeworkJdbc jdbc0 = (StudentHomeworkJdbc) applicationContext0.getBean("jdbc1");

    ApplicationContext applicationContext1 = new AnnotationConfigApplicationContext(jdbc.HomeworkJdbc.class);
    HomeworkJdbc jdbc1 = (HomeworkJdbc) applicationContext1.getBean("jdbc0");

    ApplicationContext applicationContext2 = new AnnotationConfigApplicationContext(jdbc.StudentJdbc.class);
    StudentJdbc jdbc2 = (StudentJdbc) applicationContext2.getBean("jdbc2");

    @RequestMapping(path = "/to_addHomework", method = RequestMethod.GET)
    public ModelAndView to_addHomework(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("addHomework");
    }

    @RequestMapping(path = "/to_addStudent", method = RequestMethod.GET)
    public ModelAndView to_addStudent(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("addStudent");
    }

    @RequestMapping(path = "/to_showHomework", method = RequestMethod.GET)
    public ModelAndView to_showHomework(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("showHomework");
    }

    @RequestMapping(path = "/tto_first", method = RequestMethod.GET)
    public ModelAndView tto_first(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("teacherTable");
    }
    @RequestMapping(path = "/submitHomework", method = RequestMethod.GET)
    public ModelAndView submitHomework(){



        return new ModelAndView("submitHomework");
    }
    @RequestMapping(path = "/studentTable", method = RequestMethod.GET)
    public ModelAndView studentTable(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("studentTable");
    }

    @RequestMapping(path = "/sto_first", method = RequestMethod.GET)
    public ModelAndView sto_first(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("showHomework");
    }

    @RequestMapping(path = "/teacherTable", method = RequestMethod.GET)
    public ModelAndView teacherTable(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("teacherTable");
    }

    @RequestMapping(path = "/showAllHomework", method = RequestMethod.GET)
    public ModelAndView showAllHomework(){

        List<Homework> list = jdbc1.showHomework();

        return new ModelAndView("showAllHomework", "list", list);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView allHomework(){
        //读取所有作业内容
        List<StudentHomework> list = jdbc0.selectAll();

        return new ModelAndView("index", "list", list);
    }

    @RequestMapping(path = "/addHomework", method = RequestMethod.POST)
    public void addHomework(){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        try {
            request.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Homework homework = new Homework();

        homework.setTitle(request.getParameter("title"));
        homework.setContent(request.getParameter("content"));
        Date date = new Date();
        homework.setCreateTime(date);

        boolean result = jdbc1.addHomework(homework);

        request.setAttribute("isOK", result);    //用来判断是否添加作业成功
        request.setAttribute("type","addHomework");

        try {
            request.getRequestDispatcher("t_return.jsp").forward(request,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path = "/addStudent", method = RequestMethod.POST)
    public void addStudent() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse resp = attributes.getResponse();

        try {
            request.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Student student = new Student();
        student.setId(Long.parseLong(request.getParameter("id")));
        student.setName(request.getParameter("name"));
        Date date = new Date();
        student.setCreateTime(date);

        boolean result = jdbc2.addStudent(student);

        request.setAttribute("isOK", result);  //用来判断是否添加作业成功
        request.setAttribute("type", "addStudent");
        try {
            request.getRequestDispatcher("t_return.jsp").forward(request, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(path = "/willSubmit", method = RequestMethod.GET)
    public ModelAndView willSubmit(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String id = request.getParameter("id");

        //读取指定id的作业内容详细信息
        Homework homework = jdbc1.showHomeworkDetails(id);//访问数据库
        return new ModelAndView("submitHomework", "homework", homework);
    }
        @RequestMapping(path = "/submit", method = RequestMethod.POST)
        public void submit(){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req = attributes.getRequest();
            HttpServletResponse resp = attributes.getResponse();

            try {
                req.setCharacterEncoding("utf-8");//设置编码，以防表单提交的内容乱码
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            StudentHomework studentHomework = new StudentHomework();

            studentHomework.setStudentId(Long.parseLong(req.getParameter("studentId")));
            studentHomework.setHomeworkId(Long.parseLong(req.getParameter("homeworkId")));
            studentHomework.setHomeworkTitle(req.getParameter("title"));
            studentHomework.setHomeworkContent(req.getParameter("content"));
            Date date = new Date();
            studentHomework.setCreateTime(date);

            boolean result = jdbc1.addStudentHomework(studentHomework);

            req.setAttribute("isOK", result);   //用于判断是否提交成功
            req.setAttribute("type","addStudentHomework");
            try {
                req.getRequestDispatcher("t_return.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


}
