package cgg.springboot.rest.validation.springbootrestvalidation.payload;

import java.util.List;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
