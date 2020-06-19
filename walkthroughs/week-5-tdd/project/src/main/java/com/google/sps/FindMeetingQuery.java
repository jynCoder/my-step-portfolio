// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import com.google.sps.TimeRange;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Comparator;
import com.google.sps.Event;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    // throw new UnsupportedOperationException("TODO: Implement this method.");
    Event previousAtt = new Event("Start", TimeRange.fromStartEnd(0,0, false), Collections.emptySet());
    List<Event> listOfEvents = new ArrayList<Event>(events);
    Collections.sort(listOfEvents, ORDER_BY_START);
    Collection<TimeRange> timesOfMeetings = new ArrayList<TimeRange>();

    for(Event event : listOfEvents){
        if(eventAtt(event, request.getAttendees())){
            if (event.getWhen().overlaps(previousAtt.getWhen())){
                previousAtt = previousAtt.getWhen().end() < event.getWhen().end() ? event : previousAtt;
        
        }else{
            addMeeting(event.getWhen().start(), previousAtt.getWhen().end(), request.getDuration(), timesOfMeetings);
            previousAtt = event;
        }
     }
   }
        addMeeting(TimeRange.END_OF_DAY + 1, previousAtt.getWhen().end(), request.getDuration(), timesOfMeetings);
        return timesOfMeetings;
  }

  public void addMeeting(int nextStartTime, int prevEndTime, long duration, Collection<TimeRange> timeOfMeetings){
        if(nextStartTime - prevEndTime >= duration){
            timeOfMeetings.add(TimeRange.fromStartEnd(prevEndTime, nextStartTime, false));
        }
    }

  public boolean eventAtt(Event event, Collection<String> attendees){
        for (String attendee : attendees){
            if (event.getAttendees().contains(attendee)){
                return true;
            }
        }
        return false;
    }

  public static final Comparator<Event> ORDER_BY_START = new Comparator<Event>(){
       @Override
        public int compare(Event one, Event two){
            return Long.compare(one.getWhen().start(), two.getWhen().start());
        }
    }; 
}
