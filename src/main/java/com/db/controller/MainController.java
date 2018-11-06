package com.db.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.db.entities.Record;
import com.db.services.RecordService;


@Controller
public class MainController {

    @Autowired
    RecordService recordService;

    @RequestMapping("/")
    public String mainPage(Model model) {
        Record record = new Record();
        model.addAttribute("record", record);
        return "main";
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public String addRecord(@ModelAttribute Record record, Model model) {
        if (!record.getComment().isEmpty()) {
            recordService.save(record);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(Model model) {
        List<Record> list = recordService.getAll();
        model.addAttribute("list", list);
        return "list";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.DELETE)
    public Integer deleteRecord(@RequestBody Record record) {
        recordService.delete(record);
        return record.getId();
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.PUT)
    public Integer updateRecord(@RequestBody Record record) {
        recordService.save(record);
        return record.getId();
    }

}

