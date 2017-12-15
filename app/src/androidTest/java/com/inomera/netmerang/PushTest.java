package com.inomera.netmerang;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.inomera.netmerang.activities.TransactionalPushActivity;
import com.netmera.Netmera;
import com.netmera.NetmeraPushStyle;
import com.netmera.NetmeraUser;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;


/**
 * @author Ogun Gulec
 */

public class PushTest {

  public static final String CONTENT_SHORT_PUSH = "this is a short push";
  public static final String CONTENT_MEDIUM_PUSH = "this is a medium push. Lorem ipsum dolor sit amet, ut alii doming persecuti eos, eos te movet populo propriae. Pri prima intellegebat ex, vix nobis laoreet admodum no. Vis te causae instructior, pri id";
  public static final String CONTENT_LONG_PUSH = "this is a long push. Lorem ipsum dolor sit amet, no mei error ornatus, qui causae partiendo et. Mea paulo tantas consetetur ne. Pro labore omittam te. Mel ut solum primis utamur, ignota lucilius consequat ad eam. Sit stet possit et. Civibus accumsan sed no, ex usu elit ceteros voluptaria, eu sea melius tritani salutandi. Vel cu alii iuvaret moderatius, cu prima facilisis ius, assum.";
  public static final String CONTENT_INTERACTIVE_PUSH = "this is an interactive push";
  public static final String CONTENT_INTERACTIVE_DEEPLINK_PUSH = "this is an interactive deeplink push";
  public static final String CONTENT_IMAGE_PUSH = "this is an image push for android";
  public static final String TITLE_IMAGE_PUSH = "hollandiyaaa";
  public static final String IMAGE_PATH_IMAGE_PUSH = "http://www.videoizle.co/resimler/1_143886/kisin-yuzen-adam-educatedear-remix.jpg";
  public static final String SUB_TEXT_IMAGE_PUSH = "hello tiger chicky face";
  public static final String CONTENT_INTERACTIVE_IMAGE_PUSH = "this is an interactive image push for android";
  public static final String TITLE_INTERACTIVE_IMAGE_PUSH = "hollandiyaa";
  public static final String IMAGE_PATH_INTERACTIVE_IMAGE_PUSH = "http://www.videoizle.co/resimler/1_143886/kisin-yuzen-adam-educatedear-remix.jpg";
  public static final String SUB_TEXT_INTERACTIVE_IMAGE_PUSH = "vucut direniyor efendim";
  public static final String CONTENT_BIG_TEXT_PUSH = "this is a bigtext push";
  public static final String TITLE_BIG_TEXT_PUSH = "hollandiya";
  public static final String SUB_TEXT_BIG_TEXT_PUSH = "vucut saglam efendim";
  public static final String BIG_TEXT_BIG_TEXT_PUSH = "I am fabulous, fabulous. Hello tiger chicky face. He is so nice, very very nice. Table for twoooo. I am fabulous, fabulous. Hello tiger chicky face. He is so nice, very very nice. Table for twoooo I am fabulous, fabulous. Hello tiger chicky face. He is so nice, very very nice. Table for twoooo I am fabulous, fabulous. Hello tiger chicky face. He is so nice, very very nice. Table for twoooo I am fabulous, fabulous. Hello tiger chicky face. He is so nice, very very nice. Table for twoooo";
  @Rule
  public ActivityTestRule<TransactionalPushActivity> activityTestRule = new ActivityTestRule<>(TransactionalPushActivity.class);

  @Before
  public void setup() {
    Espresso.registerIdlingResources(PushRegisterIdlingResource.getIdlingResource(), PushIdlingResource.getIdlingResource());
    NetmeraUser netmeraUser = new NetmeraUser();
    netmeraUser.setUserId(NGApplication.userId);
    Netmera.updateUser(netmeraUser);
  }

  @After
  public void reset() {
    PushIdlingResource.reset();
  }

  @Test
  public void testShortPush() {
    performClick(R.id.send_short_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_SHORT_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_SHORT_PUSH);
  }

  @Test
  public void testMediumPush() {
    performClick(R.id.send_medium_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_MEDIUM_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_MEDIUM_PUSH);
  }

  @Test
  public void testLongPush() {
    performClick(R.id.send_long_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_LONG_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_LONG_PUSH);
  }

  @Test
  public void testInteractivePush() {
    performClick(R.id.send_interactive_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_INTERACTIVE_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_INTERACTIVE_PUSH);
  }

  @Test
  public void testInteractiveDeeplinkPush() {
    performClick(R.id.send_interactive_deeplink_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_INTERACTIVE_DEEPLINK_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_INTERACTIVE_DEEPLINK_PUSH);
  }

  @Test
  public void testBigImagePush() {
    performClick(R.id.send_big_image_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_BIG_IMAGE_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_IMAGE_PUSH);
    assertContentTitle(pushStyle, TITLE_IMAGE_PUSH);
    assertBigPictureImagePath(pushStyle, IMAGE_PATH_IMAGE_PUSH);
    assertSubText(pushStyle, SUB_TEXT_IMAGE_PUSH);
  }

  @Test
  public void testInteractiveBigImagePush() {
    performClick(R.id.send_interactive_big_image_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_INTERACTIVE_BIG_IMAGE_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_INTERACTIVE_IMAGE_PUSH);
    assertContentTitle(pushStyle, TITLE_INTERACTIVE_IMAGE_PUSH);
    assertBigPictureImagePath(pushStyle, IMAGE_PATH_INTERACTIVE_IMAGE_PUSH);
    assertSubText(pushStyle, SUB_TEXT_INTERACTIVE_IMAGE_PUSH);
  }

  @Test
  public void testBigTextPush() {
    performClick(R.id.send_big_text_push);
    checkPushId(TransactionalPushActivity.NOTIFICATION_KEY_BIG_TEXT_PUSH);
    NetmeraPushStyle pushStyle = activityTestRule.getActivity().getNetmeraPushObject().getPushStyle();
    assertContentText(pushStyle, CONTENT_BIG_TEXT_PUSH);
    assertContentTitle(pushStyle, TITLE_BIG_TEXT_PUSH);
    assertSubText(pushStyle, SUB_TEXT_BIG_TEXT_PUSH);
    assertBigText(pushStyle, BIG_TEXT_BIG_TEXT_PUSH);

  }

  private void performClick(int id) {
    onView(withId(id)).perform(click());
  }

  private void checkPushId(String text) {
    onView(withId(R.id.text_view_received_push_id)).check(matches(withText(text)));
  }

  private void assertContentTitle(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getContentTitle(), text);
  }

  private void assertContentText(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getContentText(), text);
  }

  private void assertBigContentTitle(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getBigContentTitle(), text);
  }

  private void assertSubText(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getSubText(), text);
  }

  private void assertBigPictureImagePath(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getBigPicturePath(), text);
  }

  private void assertBigText(NetmeraPushStyle pushStyle, String text) {
    assertEquals(pushStyle.getBigContentText(), text);
  }
}