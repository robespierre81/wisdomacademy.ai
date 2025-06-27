package com.bodiva.wisdomacademy.backend.service;

import com.bodiva.wisdomacademy.backend.dto.AssessmentRequest;
import com.bodiva.wisdomacademy.backend.model.Answer;
import com.bodiva.wisdomacademy.backend.model.Assessment;
import com.bodiva.wisdomacademy.backend.model.Question;
import com.bodiva.wisdomacademy.backend.repository.AssessmentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssessmentService {

    private final AssessmentRepository assessmentRepository;

    public Assessment createAssessment(AssessmentRequest request) {
        Assessment assessment = new Assessment();
        assessment.setTitle(request.getTitle());
        assessment.setDescription(request.getDescription());
        assessment.setTotalQuestions(request.getQuestions().size());

        List<Question> questions = request.getQuestions().stream().map(qr -> {
            Question q = new Question();
            q.setText(qr.getText());
            q.setAssessment(assessment);

            List<Answer> answers = qr.getAnswers().stream().map(ar -> {
                Answer a = new Answer();
                a.setText(ar.getText());
                a.setCorrect(ar.isCorrect());
                a.setQuestion(q);
                return a;
            }).collect(Collectors.toList());

            q.setAnswers(answers);
            return q;
        }).collect(Collectors.toList());

        assessment.setQuestions(questions);
        return assessmentRepository.save(assessment);
    }

    public Assessment updateAssessment(Long id, AssessmentRequest request) {
        Assessment existing = assessmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        existing.setTitle(request.getTitle());
        existing.setDescription(request.getDescription());
        existing.getQuestions().clear();

        List<Question> questions = request.getQuestions().stream().map(qr -> {
            Question q = new Question();
            q.setText(qr.getText());
            q.setAssessment(existing);

            List<Answer> answers = qr.getAnswers().stream().map(ar -> {
                Answer a = new Answer();
                a.setText(ar.getText());
                a.setCorrect(ar.isCorrect());
                a.setQuestion(q);
                return a;
            }).collect(Collectors.toList());

            q.setAnswers(answers);
            return q;
        }).collect(Collectors.toList());

        existing.setQuestions(questions);
        existing.setTotalQuestions(questions.size());

        return assessmentRepository.save(existing);
    }

    public void deleteAssessment(Long id) {
        assessmentRepository.deleteById(id);
    }

    public List<Assessment> getAllAssessments() {
        return assessmentRepository.findAll();
    }
}
