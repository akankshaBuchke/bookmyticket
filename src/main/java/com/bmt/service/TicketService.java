/**
 * 
 */
package com.bmt.service;

import com.bmt.dto.BookTicketRequestDto;
import com.bmt.dto.TicketDto;

/**
 * @author naveen
 *
 * @date 05-Sep-2019
 */
public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}