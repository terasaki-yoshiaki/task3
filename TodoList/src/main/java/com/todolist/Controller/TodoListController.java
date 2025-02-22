package com.todolist.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.todolist.Model.DAO.TodoListDAO;
import com.todolist.Model.DTO.TodoListDTO;


		

@Controller
public class TodoListController {
    TodoListDAO dao = new TodoListDAO();

    @GetMapping("/")
    public String index(Model model) {
        List<TodoListDTO> list = dao.select();
        model.addAttribute("todolist", list);
        return "index.html";
    }
    
    @PostMapping("/form")
    public String form() {
        return "form.html";
    }
    
    @PostMapping("/editform")
    public String editfrom(Model model) {
        List<TodoListDTO> list = dao.select();
        model.addAttribute("todolist", list);
        return "custom.html";
    }
    
    
    @PostMapping("/delete")
	//DTOからDAOへ
	public String delete(@ModelAttribute TodoListDTO dto, Model model) {
        
        //DAOを呼び出して処理
        TodoListDAO todolistDAO = new TodoListDAO();
        todolistDAO.delete(dto);
        //getMappingの処理を同じ処理をさせたい
        return "redirect:/";
    }
}