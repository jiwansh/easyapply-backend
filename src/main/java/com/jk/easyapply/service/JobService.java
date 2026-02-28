package com.jk.easyapply.service;

import com.jk.easyapply.dto.JobDTO;
import com.jk.easyapply.entity.Job;
import com.jk.easyapply.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // Convert Entity → DTO
    private JobDTO convertToDTO(Job job){
        return JobDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .company(job.getCompany())
                .location(job.getLocation())
                .batch(job.getBatch())
                .techStack(job.getTechStack())
                .shortDescription(job.getShortDescription())
                .fullDescription(job.getFullDescription())
                .applyLink(job.getApplyLink())
                .postedDate(job.getPostedDate().toString())
                .build();
    }

    // Convert DTO → Entity
    private Job convertToEntity(JobDTO dto){
        return Job.builder()
                .title(dto.getTitle())
                .company(dto.getCompany())
                .location(dto.getLocation())
                .batch(dto.getBatch())
                .techStack(dto.getTechStack())
                .shortDescription(dto.getShortDescription())
                .fullDescription(dto.getFullDescription())
                .applyLink(dto.getApplyLink())
                .postedDate(LocalDate.now())
                .build();
    }

    // Add job
    public JobDTO addJob(JobDTO dto){
        Job job = convertToEntity(dto);
        Job saved = jobRepository.save(job);
        return convertToDTO(saved);
    }

    // Get all jobs
    public List<JobDTO> getAllJobs(){
        return jobRepository.findAll(
                        org.springframework.data.domain.Sort.by("postedDate").descending()
                )
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


    // Get job by id
    public JobDTO getJobById(Long id){
        Job job = jobRepository.findById(id).orElse(null);
        if(job == null) return null;
        return convertToDTO(job);
    }

    // update job
    public JobDTO updateJob(Long id, JobDTO dto){

        Job job = jobRepository.findById(id).orElse(null);
        if(job == null) return null;

        job.setTitle(dto.getTitle());
        job.setCompany(dto.getCompany());
        job.setLocation(dto.getLocation());
        job.setBatch(dto.getBatch());
        job.setTechStack(dto.getTechStack());
        job.setShortDescription(dto.getShortDescription());
        job.setFullDescription(dto.getFullDescription());
        job.setApplyLink(dto.getApplyLink());

        Job updated = jobRepository.save(job);
        return convertToDTO(updated);
    }


    // Delete job
    public void deleteJob(Long id){
        jobRepository.deleteById(id);
    }

    // Search by tech
    public List<JobDTO> searchByTech(String tech){
        return jobRepository.findByTechStackContainingIgnoreCase(tech)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Search by batch
    public List<JobDTO> searchByBatch(String batch){
        return jobRepository.findByBatch(batch)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Search by title keyword
    public List<JobDTO> searchByKeyword(String keyword){
        return jobRepository.findByTitleContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // pagination + sorting
    public List<JobDTO> getJobsPaginated(int page, int size){

        Pageable pageable = PageRequest.of(page, size);
        Page<Job> jobPage = jobRepository.findAll(pageable);

        return jobPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }


}
