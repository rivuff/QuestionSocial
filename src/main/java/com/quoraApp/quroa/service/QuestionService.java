package com.quoraApp.quroa.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.quoraApp.quroa.adapter.QuestionResponseAdapter;
import com.quoraApp.quroa.dto.CreateQuestionRqstDto;
import com.quoraApp.quroa.dto.QuestionResponseDto;
import com.quoraApp.quroa.model.Question;
import com.quoraApp.quroa.repository.QuestionRepository;
import com.quoraApp.quroa.util.CursorUtil;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class QuestionService implements IQuestionService {
    
    private final QuestionRepository questionRepository;


    @Override
    public Mono<QuestionResponseDto> createQuestion(CreateQuestionRqstDto questionDTO) {
        Question question = Question.builder().
                                title(questionDTO.getTitle())
                                .description(questionDTO.getDescription())
                                .authorId(questionDTO.getAuthorId())
                                .build();

        return questionRepository.save(question)
                .map(QuestionResponseAdapter::toQuestionResponseDto)
                .doOnSuccess(response -> System.out.println("Question rcreated sucessfully" + response))
                .doOnError(error -> System.out.println("Error found" + error));
    }

    @Override
    public Flux<Question> getAllQuestionsByAuthorId(String authorId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllQuestionsByAuthorId'");
    }

    @Override
    public Flux<QuestionResponseDto> searchQuestionOnQuery(String query, int offset, int page){
        return questionRepository.searchQuestionOnQuery(query, PageRequest.of(page, offset))
                    .map(QuestionResponseAdapter::toQuestionResponseDto)
                    .doOnError(error -> System.out.println("This is an error "+ error))
                    .doOnComplete(() -> System.out.println("This is right"));
    }


    @Override
    public Flux<QuestionResponseDto> getAllQuestionWithCursor(String cursor, int size){
        PageRequest pageRequest = PageRequest.of(0, size);

        if(!CursorUtil.isValidCursor(cursor)){
            System.out.println("BLA:::::::: cursor not valid");
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                .map(QuestionResponseAdapter::toQuestionResponseDto)
                .doOnComplete(()-> System.out.println("fetched all results"))
                .doOnError((e)-> System.out.println("Something went wrong"+e.getMessage()));
        }else{
            LocalDateTime currentTimeStamp = CursorUtil.parseCursor(cursor);

            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAt(currentTimeStamp, pageRequest)
                .map(QuestionResponseAdapter::toQuestionResponseDto)
                .doOnComplete(()-> System.out.println("fetched all results"))
                .doOnError((e)-> System.out.println("Something went wrong"+e.getMessage()));
                
        }
    }

    @Override
    public Mono<QuestionResponseDto> getQuestionById(String id){
        return questionRepository.findById(id).map(
            ques -> QuestionResponseAdapter.toQuestionResponseDto(ques)
        );

    }

}
