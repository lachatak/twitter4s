package com.danielasfregola.twitter4s.entities.streaming.user

import java.util.Date

import com.danielasfregola.twitter4s.entities.enums.EventCode
import com.danielasfregola.twitter4s.entities.enums.SimpleEventCode.SimpleEventCode
import com.danielasfregola.twitter4s.entities.enums.TweetEventCode.TweetEventCode
import com.danielasfregola.twitter4s.entities.enums.TwitterListEventCode.TwitterListEventCode
import com.danielasfregola.twitter4s.entities.{Tweet, TwitterList, User}
import com.danielasfregola.twitter4s.entities.streaming.UserStreamingMessage

/** Notifications about non-Tweet events are also sent over a user stream.
  * The values present will be different based on the type of event.
  * For more information see
  * <a href="https://dev.twitter.com/streaming/overview/messages-types#Events_event" target="_blank">
  *   https://dev.twitter.com/streaming/overview/messages-types#Events_event</a>.
  */
abstract class Event[T](created_at: Date, event: EventCode#Value, target: User, source: User, target_object: Option[T])
    extends UserStreamingMessage

case class SimpleEvent(created_at: Date, event: SimpleEventCode, target: User, source: User, target_object: Option[String])
    extends Event(created_at, event, target, source, target_object)

case class TweetEvent(created_at: Date, event: TweetEventCode, target: User, source: User, target_object: Tweet)
    extends Event(created_at, event, target, source, Some(target_object))

case class TwitterListEvent(created_at: Date, event: TwitterListEventCode, target: User, source: User, target_object: TwitterList)
    extends Event(created_at, event, target, source, Some(target_object))
