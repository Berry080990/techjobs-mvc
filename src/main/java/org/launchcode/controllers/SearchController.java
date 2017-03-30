package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController{
        static HashMap<String, String> columnChoices = new HashMap<>();
        private static ArrayList<HashMap<String, String>> allJobs;


        public SearchController () {
            columnChoices.put("core competency", "Skill");
            columnChoices.put("employer", "Employer");
            columnChoices.put("location", "Location");
            columnChoices.put("position type", "Position Type");
            columnChoices.put("all", "All");
        }

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value = "results")
        public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
            ArrayList<HashMap<String, String>> jobs = new ArrayList<>();


        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        if(searchType.equals("all")) {
            for (HashMap<String, String> job : JobData.findAll()) {
                for (String key : job.keySet()) {
                    String value = job.get(key);

                    if (value.toLowerCase().contains(searchTerm.toLowerCase())) {
                        jobs.add(job);
                        model.addAttribute("jobs", jobs);
                        break;
                    }
                }
            }
        }else{
            for (HashMap<String, String> job : JobData.findAll()) {

                String aValue=job.get(searchType); //
                if (aValue.toLowerCase().contains(searchTerm.toLowerCase())){

                    jobs.add(job);
                    model.addAttribute("jobs", jobs);
                }



            }
        }
        return "search";


    }

}
