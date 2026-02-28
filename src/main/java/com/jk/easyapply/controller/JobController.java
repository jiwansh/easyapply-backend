package com.jk.easyapply.controller;

import com.jk.easyapply.dto.JobDTO;
import com.jk.easyapply.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping
    public JobDTO addJob(@RequestBody @jakarta.validation.Valid JobDTO dto){
        return jobService.addJob(dto);
    }


    @GetMapping
    public List<JobDTO> getAllJobs(){
        return jobService.getAllJobs();
    }

    @GetMapping("/{id}")
    public JobDTO getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id){
        jobService.deleteJob(id);
        return "Job deleted successfully";
    }

    @PutMapping("/{id}")
    public JobDTO updateJob(@PathVariable Long id,
                            @RequestBody JobDTO dto){
        return jobService.updateJob(id, dto);
    }


    // search by tech stack
    @GetMapping("/search/tech")
    public List<JobDTO> searchByTech(@RequestParam String tech){

        return jobService.searchByTech(tech);
    }

    // search by batch
    @GetMapping("/search/batch")
    public List<JobDTO> searchByBatch(@RequestParam String batch){
        return jobService.searchByBatch(batch);
    }

    // search by keyword
    @GetMapping("/search")
    public List<JobDTO> searchByKeyword(@RequestParam String keyword){
        return jobService.searchByKeyword(keyword);
    }

    // pagination API
    @GetMapping("/page")
    public List<JobDTO> getJobsPaginated(
            @RequestParam int page,
            @RequestParam int size){

        return jobService.getJobsPaginated(page, size);
    }

    @GetMapping("/health")
    public String health(){
        return "EasyApply backend running";
    }


}
