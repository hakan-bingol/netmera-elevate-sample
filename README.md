# Netmera SDK Sample
<html>
	<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Quick Start</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><p>This section provides information about the basic steps required in order to be able to receive push notifications sent from Netmera Dashboard on your devices.</p>
<h2 class="header-scroll"><div class="anchor waypoint" id="section-create-a-google-cloud-messaging-configuration"></div>Create A Google Cloud Messaging Configuration<a class="fa fa-anchor" href="#section-create-a-google-cloud-messaging-configuration"></a></h2>
<p>Netmera uses Google Cloud Messaging(GCM) to deliver push messages to the Android devices. Therefore, you should create and configure a project on <a href="https://developers.google.com/mobile/add?platform=android" target="_self">Google Developers Console</a>.<br>At the end of the configuration, you will see values of <em>Server API Key</em> and <em>Sender ID</em> of your project. Those values must be added to the Netmera Dashboard and <em>Sender ID</em> must be used in client during initialization.</p>
<h2 class="header-scroll"><div class="anchor waypoint" id="section-integrate-sdk"></div>Integrate SDK<a class="fa fa-anchor" href="#section-integrate-sdk"></a></h2>
<p>Netmera Android SDK is served via maven repository. All you need to do is adding following configurations to your build.gradle file. AndroidManifest and other resource configurations are done automatically via Android gradle build tool.</p>
<p>Add Netmera repository to the repositories section:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">maven</span> { <span class="cm-variable">url</span> <span class="cm-string">"http://release.netmera.com/release/android"</span> }
<span class="cm-comment">//If you support Android Oreo, you should add the following line too.</span>
<span class="cm-variable">maven</span> { <span class="cm-variable">url</span> <span class="cm-string">'https://maven.google.com'</span>}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>You should add Netmera dependency to your dependencies section. If you want to use the SDK which supports Android Oreo, you need to add the following lines to your build.gradle file.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">dependencies</span> {
  <span class="cm-variable">compile</span> <span class="cm-string">'com.netmera:netmera:3.5.2'</span>
  <span class="cm-comment">//minimum play-services version must be 11.4.0</span>
  <span class="cm-variable">compile</span> <span class="cm-string">'com.google.android.gms:play-services-gcm:11.4.0’</span>
  <span class="cm-comment">//if you are using location, please add the following line too</span>
  <span class="cm-variable">compile</span> <span class="cm-string">'com.google.android.gms:play-services-location:11.4.0’</span>
}
</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">buildscript</span> {
    <span class="cm-variable">repositories</span> {
        <span class="cm-variable">jcenter</span>()
    }
    <span class="cm-variable">dependencies</span> {
        <span class="cm-variable">classpath</span> <span class="cm-string">'com.android.tools.build:gradle:2.3.3'</span>
    }
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>If you do not support Android Oreo, you can use the version below:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">dependencies</span> {
    <span class="cm-variable">compile</span> <span class="cm-string">'com.netmera:netmera:3.4.5'</span>
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-danger ">
	<h3><i title="Danger" class="fa fa-exclamation-triangle"></i>Important Note for Obfuscating
	</h3>
	<div class="callout-body"><p>If you obfuscate your code, you should add the following lines to your proguard-rules.pro file</p>
</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">proguard-rules.pro</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"> <span class="cm-operator">-</span><span class="cm-variable">keep</span> <span class="cm-keyword">class</span> <span class="cm-def">com</span>.<span class="cm-variable">netmera</span>.<span class="cm-operator">**</span> { <span class="cm-operator">*</span>; }</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h2 class="header-scroll"><div class="anchor waypoint" id="section-initialize-netmera"></div>Initialize Netmera<a class="fa fa-anchor" href="#section-initialize-netmera"></a></h2>
<p>Add the following line of code in <strong>onCreate()</strong> method of your class that extends <strong> android.app.Application</strong>.<br>If you don't have such a class, you should create it and add it to your AndroidManifest file via <em>android:name</em> attribute of the <em>application</em> tag.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-meta">@Override</span> <span class="cm-keyword">public</span> <span class="cm-variable-3">void</span> <span class="cm-def">onCreate</span>() {
    <span class="cm-keyword">super</span>.<span class="cm-variable">onCreate</span>();
  
    <span class="cm-variable">Netmera</span>.<span class="cm-variable">init</span>(<span class="cm-keyword">this</span>, <span class="cm-string">"YOUR_GCM_SENDER_ID"</span>, <span class="cm-string">"YOUR_SDK_API_KEY"</span>);
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>YOUR_SDK_API_KEY : You can get that api key from Developers -&gt; API -&gt; Sdk Api Key from your web panel.</p>
<p>You can also enable logging with the code below. Add the following line of code in <strong>onCreate()</strong> method of your class that extends <strong> android.app.Application</strong>.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">Netmera</span>.<span class="cm-variable">logging</span>(<span class="cm-atom">true</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h2 class="header-scroll"><div class="anchor waypoint" id="section-enable-popup-presentation"></div>Enable Popup Presentation<a class="fa fa-anchor" href="#section-enable-popup-presentation"></a></h2>
<p>Popup notifications are shown within app automatically and <strong>immediately</strong> via an internal Activity class in Netmera. However, in order not to break your activity flow at the launch of the application, it is disabled by default.<br>You <strong>MUST</strong> call following method in your main activity after it is started.<br>Note: Be sure <strong>NOT</strong> to add this to a splash activity if you have any!</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">//let Netmera to be able to show popup</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">enablePopupPresentation</span>();</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-success ">
	<h3><i title="Success" class="fa fa-check-square"></i>Done
	</h3>
	<div class="callout-body"><p>Now your devices will receive the following push notification types sent via Netmera Dashboard:</p>
<ul>
<li>Standard push notifications</li><li>Interactive push notifications</li><li>Push notifications with web view content</li><li>Push notifications with deeplinks (If your application supports URL Scheme based deeplinks and you have set the application's URL Scheme to the Dashboard)</li><li>Popup style notifications</li></ul>
</div>
<div class="magic-block-callout type-info ">
	<h3><i title="Info" class="fa fa-info-circle"></i>PROGUARD
	</h3>
	<div class="callout-body"><p>This step is crucial if you use proguard:<br>Netmera uses Gson for json operations. Therefore, make sure that you have added up-to-date proguard rules of Gson to your configuration file.<br>Currently list of rules are <a href="https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg" target="_self">here</a> but you should test it and see Netmera is working properly.</p>
	
</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky9-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky9" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Create A Google Cloud Messaging Configuration</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Integrate SDK</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Initialize Netmera</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Enable Popup Presentation</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Push Notifications</h1><div class="excerpt"><p>Advanced Push Notification Management</p>
</div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><p>All of Netmera push notification types works out of box. In other words, you do not need to any implementation for any type of the notification.<br>However, Popup presentation can be customized and push related callbacks can be listened.</p>
<h2 class="header-scroll"><div class="anchor waypoint" id="section-custom-web-view-presentation"></div>Custom Web View Presentation<a class="fa fa-anchor" href="#section-custom-web-view-presentation"></a></h2>
<p>By default, Netmera provides a good looking UI when a web view content should be shown on push/popup notification action and automatically presents the web view content.</p>
<p>If you have or want to implement your own web view presentation flow, you should do following steps in order to use it:</p>
<h3 class="header-scroll"><div class="anchor waypoint" id="section-implement-a-receiver-class"></div>Implement A Receiver Class<a class="fa fa-anchor" href="#section-implement-a-receiver-class"></a></h3>
<p>You should create a class extending <strong>NetmeraWebContentBroadcastReceiver</strong> and implement its abstract <strong>onShow</strong> method. Whenever a show web content action occurs, this <strong>onShow</strong> method will be called. In this method, you should start your flow where web view will be used to show the content. Check code below for sample receiver class.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-keyword">public</span> <span class="cm-keyword">class</span> <span class="cm-def">SampleWebContentBroadcastReceiver</span> <span class="cm-keyword">extends</span> <span class="cm-variable">NetmeraWebContentBroadcastReceiver</span> {
  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">public</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onShow</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable">Bundle</span> <span class="cm-variable">bundle</span>) {
    <span class="cm-comment">//kick your web view flow</span>
  }
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-add-receiver-class-to-androidmanifest"></div>Add Receiver Class To AndroidManifest<a class="fa fa-anchor" href="#section-add-receiver-class-to-androidmanifest"></a></h3>
<p>You should add created receiver class to your manifest with <strong>com.netmera.web.content.SHOW</strong> action. Here is the sample for above class:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">XML</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">application</span>
   <span class="cm-tag cm-bracket">&gt;</span>
  ...          
  <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">receiver</span>
    <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.sample.SampleWebContentBroadcastReceiver"</span>
    <span class="cm-attribute">android:exported</span>=<span class="cm-string">"false"</span><span class="cm-tag cm-bracket">&gt;</span>
    <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">intent-filter</span><span class="cm-tag cm-bracket">&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.web.content.SHOW"</span><span class="cm-tag cm-bracket">/&gt;</span>
    <span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">intent-filter</span><span class="cm-tag cm-bracket">&gt;</span>
  <span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">receiver</span><span class="cm-tag cm-bracket">&gt;</span>
  
  ...          
<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">application</span><span class="cm-tag cm-bracket">&gt;</span></span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-let-netmera-handle-received-content"></div>Let Netmera Handle Received Content<a class="fa fa-anchor" href="#section-let-netmera-handle-received-content"></a></h3>
<p>Lastly, you must inform Netmera to show received content in your web view as follows:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">//pass your web view as parameter, content will be shown in it</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">handleWebContent</span>(<span class="cm-variable">webView</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>If you want to listen url loading actions inside your web view, instead of above you should call handle method as follows:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">Netmera</span>.<span class="cm-variable">handleWebContent</span>(<span class="cm-variable">webView</span>, <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraWebViewCallback</span>() {
      <span class="cm-meta">@Override</span>
      <span class="cm-keyword">public</span> <span class="cm-variable-3">boolean</span> <span class="cm-variable">shouldOverrideUrlLoading</span>(<span class="cm-variable">WebView</span> <span class="cm-variable">view</span>, <span class="cm-variable-3">String</span> <span class="cm-variable">url</span>) {
				<span class="cm-comment">//your implementation here</span>
        <span class="cm-comment">//return true if you will handle the url yourself, otherwise return false.</span>
        <span class="cm-keyword">return</span> <span class="cm-atom">false</span>;
      }
    });</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h2 class="header-scroll"><div class="anchor waypoint" id="section-disable-enable-popups-and-in-app-messages"></div>Disable/Enable Popups and In App Messages<a class="fa fa-anchor" href="#section-disable-enable-popups-and-in-app-messages"></a></h2>

</div>
<div class="magic-block-api-header">
	<h1 class="header-scroll is-api-header"><span id=""></span>
		<div id="section-" class="anchor waypoint"></div><a href="#section-" class="fa fa-anchor"></a>
	</h1>
</div>
<div class="magic-block-textarea"><p>When a popup notification or an in app message is received by the SDK, it immediately presents the corresponding web view content if the application is in foreground state. If application is in background state when popup is received, SDK presents the web view content whenever application comes to foreground state.</p>
<p>You may want to disable this immediate presentation behavior for cases like when your users watch a video, when they are in the middle of their favorite game level, or when they are about to finish purchasing their order. You can use the following two methods to manage this process:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">// Call this method to disable immediate popup presentation</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">disablePopupPresentation</span>();

<span class="cm-comment">// Call this method to re-enable immediate popup presentation</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">enablePopupPresentation</span>();</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-info no-title"><span class="noTitleIcon"><i title="Info" class="fa fa-info-circle"></i></span>
	<div class="callout-body"><p>In order to use in app messages, you should integrate the SDK version 3.4.0 or higher.</p>

</div>
<div class="magic-block-textarea"><p>You can customize the in app messages from the styles.xml with the attributes below</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">XML</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">&lt;!-- Custom in-app message style --&gt;</span>
    <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">style</span> <span class="cm-attribute">name</span>=<span class="cm-string">"NetmeraInAppMessageStyle"</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view width in ratio    
       <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageWidth"</span><span class="cm-tag cm-bracket">&gt;</span>@integer/netmera_default_vertical_width<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view height in ratio  
       <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageHeight"</span><span class="cm-tag cm-bracket">&gt;</span>@integer/netmera_default_vertical_height<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view weight in ratio
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageWeight"</span><span class="cm-tag cm-bracket">&gt;</span>@integer/netmera_default_vertical_weight<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view backgroundcolor
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageBackground"</span><span class="cm-tag cm-bracket">&gt;</span>@drawable/netmera_bg_in_app_message<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view cancel button image
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageCancelButtonDrawable"</span><span class="cm-tag cm-bracket">&gt;</span>@drawable/netmera_ic_action_cancel<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view title size
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageTitleSize"</span><span class="cm-tag cm-bracket">&gt;</span>@dimen/netmera_text_default_size<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view title color
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageTitleColor"</span><span class="cm-tag cm-bracket">&gt;</span>@color/primary_text_light<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view text size
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageTextSize"</span><span class="cm-tag cm-bracket">&gt;</span>@dimen/netmera_text_default_size<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view text color
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageTextColor"</span><span class="cm-tag cm-bracket">&gt;</span>@color/primary_text_light<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
        //in-app message view title and text fontPath
        <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">item</span> <span class="cm-attribute">name</span>=<span class="cm-string">"inAppMessageFontPath"</span><span class="cm-tag cm-bracket">&gt;</span>/assets/fonts/*.ttf<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">item</span><span class="cm-tag cm-bracket">&gt;</span>
    <span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">style</span><span class="cm-tag cm-bracket">&gt;</span></span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-info no-title"><span class="noTitleIcon"><i title="Info" class="fa fa-info-circle"></i></span>
	<div class="callout-body"><p>If a popup is received when popup presentation is disabled by your code, SDK will store it, and present whenever popup presentation is re-enabled. If multiple popup notifications are received during that period, SDK stores the most recent notification, and present only that one.</p>

	</div>
</div>
<div class="magic-block-textarea"><h2 class="header-scroll"><div class="anchor waypoint" id="section-push-callbacks"></div>Push Callbacks<a class="fa fa-anchor" href="#section-push-callbacks"></a></h2>
<p>Netmera has following push callbacks:</p>
<ul>
<li>Push Register</li><li>Push Receive</li><li>Push Open</li><li>Push Dismiss</li><li>Push Button Click</li></ul>
<p>If you want to be informed for one or more of those callback you should follow steps below:</p>
<h3 class="header-scroll"><div class="anchor waypoint" id="section-implement-a-receiver-class"></div>Implement A Receiver Class<a class="fa fa-anchor" href="#section-implement-a-receiver-class"></a></h3>
<p>You should create a class extending <strong>NetmeraPushBroadcastReceiver</strong> and override any callback's method. Here is a sample:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-keyword">public</span> <span class="cm-keyword">class</span> <span class="cm-def">SamplePushBroadcastReceiver</span> <span class="cm-keyword">extends</span> <span class="cm-variable">NetmeraPushBroadcastReceiver</span> {
  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">protected</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onPushRegister</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable-3">String</span> <span class="cm-variable">gcmSenderId</span>, <span class="cm-variable-3">String</span> <span class="cm-variable">pushTkoen</span>) {
		<span class="cm-comment">//if you want to know what is the push token for given gcmSenderId</span>
  }

  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">protected</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onPushReceive</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable">Bundle</span> <span class="cm-variable">bundle</span>, <span class="cm-variable">NetmeraPushObject</span> <span class="cm-variable">netmeraPushObject</span>) {
		<span class="cm-comment">//if you want to know when a push receives</span>
  }

  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">protected</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onPushOpen</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable">Bundle</span> <span class="cm-variable">bundle</span>, <span class="cm-variable">NetmeraPushObject</span> <span class="cm-variable">netmeraPushObject</span>) {
		<span class="cm-comment">//if you want to know when a push is opened</span>
  }

  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">protected</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onPushDismiss</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable">Bundle</span> <span class="cm-variable">bundle</span>, <span class="cm-variable">NetmeraPushObject</span> <span class="cm-variable">netmeraPushObject</span>) {
		<span class="cm-comment">//if you want to know when a push is dismissed</span>
  }

  <span class="cm-meta">@Override</span>
  <span class="cm-keyword">protected</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onPushButtonClicked</span>(<span class="cm-variable">Context</span> <span class="cm-variable">context</span>, <span class="cm-variable">Bundle</span> <span class="cm-variable">bundle</span>, <span class="cm-variable">NetmeraPushObject</span> <span class="cm-variable">netmeraPushObject</span>) {
		<span class="cm-comment">//if you want to know when a interactive push button is clicked</span>
  }
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-add-receiver-class-to-androidmanifest"></div>Add Receiver Class To AndroidManifest<a class="fa fa-anchor" href="#section-add-receiver-class-to-androidmanifest"></a></h3>
<p>You should add created receiver class to your manifest with <strong>ALL</strong> of the following actions.</p>
<ul>
<li>com.netmera.push.intent.REGISTER</li><li>com.netmera.push.intent.RECEIVE</li><li>com.netmera.push.intent.OPEN</li><li>com.netmera.push.intent.DISMISS</li><li>com.netmera.push.intent.BUTTON</li></ul>
<p>Here is the sample for above class:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">XML</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">application</span>
   <span class="cm-tag cm-bracket">&gt;</span>
  ...          
  <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">receiver</span> 
    <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.sample.SamplePushBroadcastReceiver"</span>
    <span class="cm-attribute">android:exported</span>=<span class="cm-string">"false"</span><span class="cm-tag cm-bracket">&gt;</span>
    <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">intent-filter</span><span class="cm-tag cm-bracket">&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.REGISTER"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.RECEIVE"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.OPEN"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.DISMISS"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.BUTTON"</span><span class="cm-tag cm-bracket">/&gt;</span>
      //If you want to use carousel push notifications, you should add the
following actions.
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.carousel.OPEN"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.carousel.PREV"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.carousel.NEXT"</span><span class="cm-tag cm-bracket">/&gt;</span>
      <span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">action</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"com.netmera.push.intent.carousel.ITEM"</span><span class="cm-tag cm-bracket">/&gt;</span>
    <span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">intent-filter</span><span class="cm-tag cm-bracket">&gt;</span>
  <span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">receiver</span><span class="cm-tag cm-bracket">&gt;</span>
  
  ...          
<span class="cm-tag cm-bracket">&lt;/</span><span class="cm-tag">application</span><span class="cm-tag cm-bracket">&gt;</span></span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-let-netmera-know-you-use-custom-receiver"></div>Let Netmera Know You Use Custom Receiver<a class="fa fa-anchor" href="#section-let-netmera-know-you-use-custom-receiver"></a></h3>
<p>This step is crucial in order not to cause inconsistent situations in Netmera's business logic and collected data.<br>You should add following line in your gradle file's <strong>defaultConfig</strong> block as follows:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">android</span> {
  ...
    <span class="cm-property">defaultConfig</span> {
      ...
        <span class="cm-comment">//following line is for using custom push receiver</span>
        <span class="cm-property">resValue</span> <span class="cm-string">"bool"</span>, <span class="cm-string">"netmera_use_default_push_receiver"</span>, <span class="cm-string">"false"</span>
      ...
    }
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky5-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky5" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">
		
	</a><!-- ngIf: node.children.length --><ul ng-if="node.children.length" class="ng-scope"><!-- ngRepeat: node in node.children --><li ng-repeat="node in node.children" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Push Callbacks</a></li><!-- end ngRepeat: node in node.children --></ul><!-- end ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
	<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Events</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-internal-events"></div>Internal Events<a class="fa fa-anchor" href="#section-internal-events"></a></h1>
<p>By default, Netmera SDK automatically tracks and reports the following behaviors about application usage:</p>
<ul>
<li>App opens</li><li>Time passed inside the application for each foreground usage</li><li>Push receipts (if configured from Dashboard)</li><li>Push opens</li><li>Push dismisses</li><li>Enter/exit actions for geofence regions if any has been set up</li><li>Location updates if history has been enabled</li><li>Actions taken inside web views presented by Netmera</li></ul>
<h1 class="header-scroll"><div class="anchor waypoint" id="section-built-in-events"></div>Built-in Events<a class="fa fa-anchor" href="#section-built-in-events"></a></h1>
<p>Other than default event tracking, SDK provides a rich set of built-in event classes which you can use to report users' corresponding behaviors inside your application.</p>
<p>Rather than accepting unstructured information inside events, SDK requires a predefined set of constraint related to attributes of events and their data types by means of these <code>NetmeraEvent</code> subclasses. This approach enables Netmera to verify type of the given event attributes, and force some attributes for a particular event type. These restrictions are crucial for providing reliable data during event analytics operations on Netmera servers.</p>
<p>You can send events easily with the following code pattern:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">// Generate event instance</span>
<span class="cm-comment">// This can be any NetmeraEvent subclass</span>
<span class="cm-variable">NetmeraEventLogin</span> <span class="cm-variable">event</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraEventLogin</span>(<span class="cm-string">"USER_ID"</span>);
<span class="cm-comment">// Send event</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">sendEvent</span>(<span class="cm-variable">event</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>Below is the list of all built-in event classes — categorized by use cases — and their sample usage:</p>
<ul>
<li>Common Events<ul>
<li>Login Event</li><li>Register Event</li><li>Search Event</li><li>Share Event</li><li>In App Purchase Event</li><li>Banner Click Event</li><li>Category View Event</li></ul>
</li><li>Commerce Events<ul>
<li>Product View Event</li><li>Product Rate Event</li><li>Product Comment Event</li><li>Order Cancel Event</li><li>Purchase Event</li><li>Cart View Event</li><li>Add To Cart Event</li><li>Remove From Cart Event</li></ul>
</li><li>Media Events<ul>
<li>Content Comment Event</li><li>Content Rate Event</li><li>Content View Event</li></ul>
</li></ul>
<h1 class="header-scroll"><div class="anchor waypoint" id="section-custom-events"></div>Custom Events<a class="fa fa-anchor" href="#section-custom-events"></a></h1>
<p>If events provided by the SDK do not completely satisfy your needs, you can also generate your own event classes using Netmera Dashboard. You may extend your custom event subclass from one of the built-in event subclasses, or from the base <code>NetmeraEvent</code> class. You can select the data type of the parameters, make them array or set them as mandatory. If you do not send a mandatory parameter you will get error(bad request) and your request will be rejected.</p>
<p>You can follow Developers -&gt; Events and click the Create New Event button to generate your custom event.</p>

</div>
<div class="magic-block-image">
	<figure><a href="https://files.readme.io/094c5d8-CustomEventCreation.png" class="block-display-image-parent block-display-image-size-smart "><img src="https://files.readme.io/094c5d8-CustomEventCreation.png"></a>
	</figure>
</div>
<div class="magic-block-textarea"><p>In the end, Netmera Dashboard will automatically generate source files for your custom event, so that you can just add them to your project and use without any hassle. </p>
<p>After you add the source files to your project, you can fire that custom event like the code below;</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">SampleEvent</span> <span class="cm-variable">event</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">SampleEvent</span>();

<span class="cm-variable">event</span>.<span class="cm-variable">setIntParameter</span>(<span class="cm-number">34</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setIntParameter</span>(<span class="cm-number">2147483647</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setStringParameter</span>(<span class="cm-string">"String Parameter"</span>);
<span class="cm-variable">List</span><span class="cm-operator">&lt;</span><span class="cm-variable-3">String</span><span class="cm-operator">&gt;</span> <span class="cm-variable">myArray</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">ArrayList</span><span class="cm-operator">&lt;&gt;</span>();
<span class="cm-variable">myArray</span>.<span class="cm-variable">add</span>(<span class="cm-string">"Member1"</span>);
<span class="cm-variable">myArray</span>.<span class="cm-variable">add</span>(<span class="cm-string">"Member2"</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setStringArrayParameter</span>(<span class="cm-variable">myArray</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setDoubleParameter</span>(<span class="cm-number">7.99</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setBooleanParameter</span>(<span class="cm-atom">true</span>); <span class="cm-comment">//true or false</span>
<span class="cm-variable">event</span>.<span class="cm-variable">setLongParameter</span>(<span class="cm-number">9223372036854775807L</span>);
<span class="cm-variable">event</span>.<span class="cm-variable">setDateParameter</span>(<span class="cm-keyword">new</span> <span class="cm-variable">Date</span>());
<span class="cm-variable">event</span>.<span class="cm-variable">setTimestampParameter</span>(<span class="cm-keyword">new</span> <span class="cm-variable">Date</span>());
        
<span class="cm-variable">Netmera</span>.<span class="cm-variable">sendEvent</span>(<span class="cm-variable">event</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky6-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky6" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Internal Events</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Built-in Events</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Custom Events</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Geofence &amp; Location</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><p>By default, Netmera SDK does not gather any location information from device. If you want to use location related features of Netmera such as geofence messages and filtering target users by location, you should make some extra implementation.</p>
<p>For using location to targeting your users, you should enable Location History from the web panel. In order to do that, follow Developers -&gt; App Info -&gt; App Config -&gt; Location History Enabled.</p>
<h1 class="header-scroll"><div class="anchor waypoint" id="section-add-google-play-services-location-library"></div>Add Google Play Services Location Library<a class="fa fa-anchor" href="#section-add-google-play-services-location-library"></a></h1>
<p>First of all, you should add following dependency your gradle file's dependency section:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Groovy</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">dependencies</span> {
  <span class="cm-variable">compile</span> <span class="cm-string">'com.google.android.gms:play-services-location:PUT_LATEST_VERSION_HERE'</span>
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-add-permissions"></div>Add Permissions<a class="fa fa-anchor" href="#section-add-permissions"></a></h1>
<p>Secondly, add following two location permissions to your AndroidManifest file.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">XML</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">uses-permission</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"android.permission.ACCESS_FINE_LOCATION"</span><span class="cm-tag cm-bracket">/&gt;</span>
<span class="cm-tag cm-bracket">&lt;</span><span class="cm-tag">uses-permission</span> <span class="cm-attribute">android:name</span>=<span class="cm-string">"android.permission.ACCESS_COARSE_LOCATION"</span><span class="cm-tag cm-bracket">/&gt;</span></span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-request-permissions"></div>Request Permissions<a class="fa fa-anchor" href="#section-request-permissions"></a></h1>
<p>Lastly, <strong>only</strong> for Android 6.0 (API Level 23) and later, location permission must be requested while app is running depending to your app's targetSdkVersion. For more info please refer to this <a href="https://developer.android.com/training/permissions/requesting.html" target="_self">link</a>.<br>The implementation of runtime permission request is cumbersome but no worries Netmera has already have permission request flow for location permissions. All you have to do is calling following method in some appropriate place of your app's flow.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">//Request runtime permission for location</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">requestPermissionsForLocation</span>();</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>After you call that method, default permission dialog will be shown to the user if app has not already been granted the location permission. After she allows, Netmera will perform location operations according to your settings on Netmera Dashboard.</p>

</div>
<div class="magic-block-callout type-success ">
	<h3><i title="Success" class="fa fa-check-square"></i>Done
	</h3>
	<div class="callout-body"><p>Now, your app can use location features of Netmera SDK.<br>Go to your app on Netmera Dashboard make following configurations according your needs:</p>
<ul>
<li>Enable location history for location tracking</li><li>Add some Geofence places for region tracking.</li></ul>

</div>
<div class="magic-block-callout type-info ">
	<h3><i title="Info" class="fa fa-info-circle"></i>Info
	</h3>
	<div class="callout-body"><p>You can set max regions for Geofence with <strong>setNetmeraMaxActiveRegions</strong> method. If you set max active regions' number greater than 100 or smaller than 0, it will be set as the default which is 100.</p>

</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky7-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky7" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Add Google Play Services Location Library</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Add Permissions</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Request Permissions</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>User</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><p>Use <code>NetmeraUser</code> class to send information about your application's users to Netmera in a structured way.</p>
<p>Typical place to inform Netmera about application user's attributes is after your users has logged in to your application. After you have information about your logged in user, you should create a <code>NetmeraUser</code> object, set values, then call <code>Netmera.updateUser()</code> method like below:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">NetmeraUser</span> <span class="cm-variable">netmeraUser</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraUser</span>();
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setUserId</span>(<span class="cm-string">"the_greatest"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setMsisdn</span>(<span class="cm-string">"001XXXXXXXXX"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setEmail</span>(<span class="cm-string">"clay@champion.com"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setName</span>(<span class="cm-string">"Muhammad Ali"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setSurname</span>(<span class="cm-string">"Clay"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setExternalSegments</span>(<span class="cm-variable">Arrays</span>.<span class="cm-variable">asList</span>(<span class="cm-string">"sports"</span>, <span class="cm-string">"box"</span>));
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setGender</span>(<span class="cm-variable">NetmeraUser</span>.<span class="cm-variable">GENDER_MALE</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setDateOfBirth</span>(<span class="cm-keyword">new</span> <span class="cm-variable">GregorianCalendar</span>(<span class="cm-number">1942</span>, <span class="cm-number">1</span>, <span class="cm-number">17</span>).<span class="cm-variable">getTime</span>());
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setMaritalStatus</span>(<span class="cm-variable">NetmeraUser</span>.<span class="cm-variable">MARITAL_STATUS_MARRIED</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setChildCount</span>(<span class="cm-number">9</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setCountry</span>(<span class="cm-string">"USA"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setState</span>(<span class="cm-string">"Arizona"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setCity</span>(<span class="cm-string">"Scottsdale"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setDistrict</span>(<span class="cm-string">"Old Town Scottsdale"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setOccupation</span>(<span class="cm-string">"Professional Boxer"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setIndustry</span>(<span class="cm-string">"Sports"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setFavoriteTeam</span>(<span class="cm-string">"My Team"</span>);
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setLanguage</span>(<span class="cm-string">"English"</span>);
<span class="cm-comment">// Send data to Netmera</span>
<span class="cm-variable">Netmera</span>.<span class="cm-variable">updateUser</span>(<span class="cm-variable">netmeraUser</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>You can also update any attribute independent from the others. </p>
<p>If you need to remove a previously set attribute from Netmera, you must set <code>null</code> for corresponding attribute. Here is an example:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">NetmeraUser</span> <span class="cm-variable">netmeraUser</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraUser</span>();
<span class="cm-comment">// This will remove previously set `email` value from Netmera</span>
<span class="cm-variable">netmeraUser</span>.<span class="cm-variable">setEmail</span>(<span class="cm-atom">null</span>);
<span class="cm-variable">Netmera</span>.<span class="cm-variable">updateUser</span>(<span class="cm-variable">netmeraUser</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-warning no-title"><span class="noTitleIcon"><i title="Warning" class="fa fa-exclamation-circle"></i></span>
	<div class="callout-body"><p>userId cannot be removed even if you set <code>null</code> to it.</p>

</div>
<div class="magic-block-textarea"><h2 class="header-scroll"><div class="anchor waypoint" id="section-custom-user"></div>Custom User<a class="fa fa-anchor" href="#section-custom-user"></a></h2>
<p>Similar to events, you can generate a custom <code>NetmeraUser</code> subclass using Netmera Dashboard if the set of built-in attributes is not enough for use case.</p>
<p>Netmera will automatically generate the source files for your custom user class, so that you can easily use them to send information about your custom attributes.</p>

</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky8-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky8" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Custom User</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Sound &amp; Vibration</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-sending-a-push-notification-with-sound"></div>Sending a Push Notification with Sound<a class="fa fa-anchor" href="#section-sending-a-push-notification-with-sound"></a></h1>
<p>Sending push notifications with sound can be done with enabling "Play Sound" section on "What" tab while creating a new push. If you select "Default" the default sound of the device will be ringed if the sound is enabled from the device.</p>

</div>
<div class="magic-block-image">
	<figure><a href="https://files.readme.io/3228c5c-PlaySoundAndroid.png" class="block-display-image-parent block-display-image-size-smart "><img src="https://files.readme.io/3228c5c-PlaySoundAndroid.png"></a>
	</figure>
</div>
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-sending-a-push-notification-with-vibration"></div>Sending a Push Notification with Vibration<a class="fa fa-anchor" href="#section-sending-a-push-notification-with-vibration"></a></h1>
<p>For using vibration on your Android application, you should add the following permission to your Android.manifest file, as a direct child of the manifest element:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Android Manifest</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-operator">&lt;</span><span class="cm-variable">uses</span><span class="cm-operator">-</span><span class="cm-variable">permission</span> <span class="cm-variable">android</span>:<span class="cm-variable">name</span><span class="cm-operator">=</span><span class="cm-string">"android.permission.VIBRATE"</span> <span class="cm-operator">/&gt;</span></span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>After that, you should enable vibration from the What tab while creating a campaign.</p>

</div>
<div class="magic-block-image">
	<figure><a href="https://files.readme.io/638eef4-Vibrate.png" class="block-display-image-parent block-display-image-size-smart "><img src="https://files.readme.io/638eef4-Vibrate.png"></a>
	</figure>
</div>
<div class="magic-block-textarea"><h1 class="header-scroll"><div class="anchor waypoint" id="section-adding-custom-sound-to-your-project"></div>Adding Custom Sound to Your Project<a class="fa fa-anchor" href="#section-adding-custom-sound-to-your-project"></a></h1>
<p>To perform this function, you should pay attention to some constraints. One of them is that your sound file should be included in your main bundle. If this condition does not meet, default sound of your phone will be played when receiving the push notification, instead. Another constraint is that you can use just the audio data packaged in an ‘aiff’, ‘wav’ or ‘caf’ file (i.e. your_sound_name.aiff). Last constraint is that you can add at most 30-seconds-length custom sounds. If any sound exceeds 30 seconds, the default sound of your phone will be played instead of your custom one. You can select any sound name in the drop-box menu. All items in the menu are the sound files which extensions satisfy the push sound extensions (.aiff, .wav and .caf).</p>
<p>After the sound file is added to your project, you should create a new sound from the web panel. Go to Developers -&gt; Sound -&gt; Create New Sound. While creating the sound you shall not use any file extensions on Sound FileName.</p>

</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky9-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky9" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Sending a Push Notification with Sound</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Sending a Push Notification with Vibration</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Adding Custom Sound to Your Project</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
<div ng-if="!suggestedEdits.isEnabled" class="ng-scope"><header id="content-head"><div class="row clearfix"><div class="col-xs-9"><h1>Inbox</h1><div class="excerpt"></div></div><div class="col-xs-3"><a ng-click="suggestedEdits.enable()" href="" class="suggestEdits"><i class="icon icon-register"></i>Suggest Edits</a></div></div></header><div id="content-container" scroll-spy="" class="grid-container-fluid ng-scope"><section ng-class="{&quot;grid-75&quot;: false != true &amp;&amp; ss.toc.length , &quot;grid-100&quot;: false != false || !ss.toc.length}" class="content-body grid-75">
<!-- Gah, you have to manually restart the app if you change this!-->
<div class="magic-block-textarea"><p>If your application needs information about the push notifications that are previously sent to device by Netmera, you can use <code>NetmeraInbox</code> class to fetch that information from Netmera.</p>
<p>The most common use case for this would be to show the list of notifications inside your application in an inbox-style interface.</p>
<p><code>NetmeraInbox</code> is the core class providing methods and properties needed for operations on push notifications like fetching push objects or updating push objects' status, but you can not directly initialize a <code>NetmeraInbox</code> instance. You get an instance from SDK, then operate on that instance for future inbox actions. Here is the common workflow to use inbox feature of Netmera.</p>
<h3 class="header-scroll"><div class="anchor waypoint" id="section-1-determine-properties-of-push-notifications-to-fetch"></div>1. Determine properties of push notifications to fetch<a class="fa fa-anchor" href="#section-1-determine-properties-of-push-notifications-to-fetch"></a></h3>
<p>You must first define filtering properties by creating a <code>NetmeraInboxFilter</code> instance. You can determine which push notifications will be included in the fetched list by setting related properties of this <code>NetmeraInboxFilter</code> instance.</p>
<p><code>NetmeraInboxFilter</code> class provides filtering according to the following options:</p>
<ul>
<li>Inbox Status : Read / Unread / Deleted</li><li>Categories : Categories to which push notifications are belong.</li><li>Including expired push notifications or not.</li><li>Page Size : This is not to filter, but to determine the size of chunks which will be gathered during one request.</li></ul>
<p>Here is a sample code to determine filtering options: </p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">NetmeraInboxFilter</span> <span class="cm-variable">filter</span> <span class="cm-operator">=</span> <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraInboxFilter</span>.<span class="cm-variable">Builder</span>()
        <span class="cm-comment">//Default value is Integer.MAX_VALUE</span>
        .<span class="cm-variable">pageSize</span>(<span class="cm-number">20</span>)
        <span class="cm-comment">// Default value is NetmeraPushObject.STATUS_READ | NetmeraPushObject.STATUS_UNREAD</span>
        .<span class="cm-variable">status</span>(<span class="cm-variable">NetmeraPushObject</span>.<span class="cm-variable">STATUS_ALL</span>)
        <span class="cm-comment">//Default value is null</span>
        .<span class="cm-variable">categories</span>(<span class="cm-variable">Arrays</span>.<span class="cm-variable">asList</span>(<span class="cm-string">"category_1"</span>, <span class="cm-string">"category_2"</span>))
        <span class="cm-comment">//Default value is false</span>
        .<span class="cm-variable">includeExpiredObjects</span>(<span class="cm-atom">true</span>)
        <span class="cm-comment">//create filter object from builder</span>
        .<span class="cm-variable">build</span>();</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-2-fetch-the-first-page-and-get-the-netmerainbox-instance"></div>2. Fetch the first page and get the <code>NetmeraInbox</code> instance<a class="fa fa-anchor" href="#section-2-fetch-the-first-page-and-get-the-netmerainbox-instance"></a></h3>
<p>Now, you can request from Netmera to return the list of push notification objects matching with the filter object using the following code:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">Netmera</span>.<span class="cm-variable">fetchInbox</span>(<span class="cm-variable">filter</span>, <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraInbox</span>.<span class="cm-variable">NetmeraInboxFetchCallback</span>() {
        <span class="cm-meta">@Override</span> <span class="cm-keyword">public</span> <span class="cm-variable-3">void</span> <span class="cm-variable">NetmeraInbox</span>.<span class="cm-variable">NetmeraInboxFetchCallback</span>(<span class="cm-variable">NetmeraInbox</span> <span class="cm-variable">netmeraInbox</span>, <span class="cm-variable">NetmeraError</span> <span class="cm-variable">error</span>) {
          <span class="cm-keyword">if</span> (<span class="cm-variable">error</span> <span class="cm-operator">!=</span> <span class="cm-atom">null</span>) {
            <span class="cm-comment">//handle error</span>
            <span class="cm-variable">Toast</span>.<span class="cm-variable">makeText</span>(<span class="cm-variable">context</span>, <span class="cm-variable">error</span>.<span class="cm-variable">getMessage</span>(), <span class="cm-variable">Toast</span>.<span class="cm-variable">LENGTH_LONG</span>).<span class="cm-variable">show</span>();
            <span class="cm-keyword">return</span>;
          }
          <span class="cm-comment">// Store returned inbox object for future operations</span>
          <span class="cm-variable">mInbox</span> <span class="cm-operator">=</span> <span class="cm-variable">netmeraInbox</span>;
        }
      });</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>If fetch operations succeeds, NetmeraInbox.NetmeraInboxFetchCallback's NetmeraInbox.NetmeraInboxFetchCallback method will be called with an <code>inbox</code> object which contains the first chunk of push notifications matching with given filter, and a <code>null</code> <code>error</code>. Otherwise, it will be called with a <code>null</code> <code>inbox</code> object and an <code>error</code> object containing details about the reason of failure.</p>
<p>Now you can present the list of push objects inside your application. You get the list using <code>pushObjects()</code> method of NetmeraInbox, which return a list of <code>NetmeraPushObject</code> instances. Moreover, for further operations like fetching next pages, setting status, you will be using NetmeraInbox object's methods.</p>

</div>
<div class="magic-block-callout type-warning no-title"><span class="noTitleIcon"><i title="Warning" class="fa fa-exclamation-circle"></i></span>
	<div class="callout-body"><p>Filter properties of an inbox instance could not be changed. If you need to modify filter properties, you have to create a new filter object and start a new fetch operation using <code>Netmera.fetchInbox</code> method.</p>

</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-3-fetch-next-pages-via-netmerainbox-object"></div>3. Fetch next pages via NetmeraInbox object<a class="fa fa-anchor" href="#section-3-fetch-next-pages-via-netmerainbox-object"></a></h3>
<p>After you get NetmeraInbox object which contains first page of the push notifications, you can fetch next pages with a very similar code shown above but this time via your NetmeraInbox instance. New pages are collected on already created NetmeraInbox object, therefore you can use same response handling with fetching first page.<br>NetmeraInbox has a method named <strong>hasNextPage</strong> for checking whether there exists more pages or not. You can use that method to decide whether you should fetch next page or not, to change your ui to let user know she has got to the end of list, etc.<br>Here is the sample code:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-keyword">if</span>(<span class="cm-variable">mInbox</span>.<span class="cm-variable">hasNextPage</span>()){
  <span class="cm-variable">mInbox</span>.<span class="cm-variable">fetchNextPage</span>(<span class="cm-keyword">new</span> <span class="cm-variable">NetmeraInbox</span>.<span class="cm-variable">NetmeraInboxFetchCallback</span>() {
        <span class="cm-meta">@Override</span> <span class="cm-keyword">public</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onFetchInbox</span>(<span class="cm-variable">NetmeraInbox</span> <span class="cm-variable">netmeraInbox</span>, <span class="cm-variable">NetmeraError</span> <span class="cm-variable">error</span>) {
          <span class="cm-comment">//handle same as former sample</span>
        }
      });
}</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-callout type-warning no-title"><span class="noTitleIcon"><i title="Warning" class="fa fa-exclamation-circle"></i></span>
	<div class="callout-body"><p>If you call fetchNextPage when there is no more next page, onFetchInbox method will be called immediately with a <strong>no next page</strong> error.</p>

</div>
<div class="magic-block-textarea"><h3 class="header-scroll"><div class="anchor waypoint" id="section-4-update-the-status-of-push-notifications"></div>4. Update the status of push notifications<a class="fa fa-anchor" href="#section-4-update-the-status-of-push-notifications"></a></h3>
<p>Push notifications may have 3 different states, which are the followings:</p>
<ul>
<li>Unread</li><li>Read</li><li>Deleted</li></ul>
<p>These three states allows you to implement a simple notification inbox interface for your users where they can read messages, mark previously read message as unread, delete messages and restore them again if needed.</p>
<p>You can make transitions among states for push notifications inside inbox using <code>updateStatus</code> method. Calling this method will start an asynchronous request to update status for given push objects, and given callback will be called upon the result of the request.<br>Here is a sample implementation which deleted the first 5 push objects from inbox:</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-variable">List</span><span class="cm-operator">&lt;</span><span class="cm-variable">NetmeraPushObject</span><span class="cm-operator">&gt;</span> <span class="cm-variable">objectsToDelete</span> <span class="cm-operator">=</span> <span class="cm-variable">inbox</span>.<span class="cm-variable">pushObjects</span>().<span class="cm-variable">subList</span>(<span class="cm-number">0</span>, <span class="cm-number">5</span>);
<span class="cm-variable">inbox</span>.<span class="cm-variable">updateStatus</span>(<span class="cm-variable">objectsToDelete</span>, <span class="cm-variable">NetmeraPushObject</span>.<span class="cm-variable">STATUS_DELETED</span>,
    <span class="cm-keyword">new</span> <span class="cm-variable">NetmeraInbox</span>.<span class="cm-variable">NetmeraInboxStatusCallback</span>() {
  <span class="cm-meta">@Override</span> <span class="cm-keyword">public</span> <span class="cm-variable-3">void</span> <span class="cm-variable">onSetStatusInbox</span>(<span class="cm-variable">NetmeraError</span> <span class="cm-variable">error</span>) {
    <span class="cm-keyword">if</span> (<span class="cm-variable">error</span> <span class="cm-operator">!=</span> <span class="cm-atom">null</span>) {
      <span class="cm-comment">//handle</span>
    }
  }
});</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div>
<div class="magic-block-textarea"><p>If operation fails for some reason, completion block will be called with a <code>nonnull</code> <code>error</code> parameter describing the reason of failure.</p>
<h3 class="header-scroll"><div class="anchor waypoint" id="section-5-get-count-for-one-or-more-status"></div>5. Get count for one or more status<a class="fa fa-anchor" href="#section-5-get-count-for-one-or-more-status"></a></h3>
<p>You can get total count of push objects according to the status info among the push objects that matches given filter. Count can be get only for one specific status as well as for combined set of status values.</p>

</div>
<div class="magic-block-code ng-scope">
	<ul class="block-code-header">
		<li><a href="" ng-click="showCode(0)" ng-class="{active: (0 == tab)}" class="active">Java</a></li>
	</ul>
	<div class="block-code-code">
		<!-- ngIf: tab == 0 --><pre ng-if="tab == 0" class="ng-scope"><code><span class="cm-s-neo"><span class="cm-comment">//get count of deleted push objects</span>
<span class="cm-variable">inbox</span>.<span class="cm-variable">countForStatus</span>(<span class="cm-variable">NetmeraPushObject</span>.<span class="cm-variable">STATUS_DELETED</span>);

<span class="cm-comment">//get count of read and unread push objects</span>
<span class="cm-variable">inbox</span>.<span class="cm-variable">countForStatus</span>(<span class="cm-variable">NetmeraPushObject</span>.<span class="cm-variable">STATUS_READ</span> <span class="cm-operator">|</span> <span class="cm-variable">NetmeraPushObject</span>.<span class="cm-variable">STATUS_UNREAD</span>);</span></code></pre><!-- end ngIf: tab == 0 -->
	</div>
</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky10-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky10" style="width: 686px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">1. Determine properties of push notifications to fetch</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">2. Fetch the first page and get the NetmeraInbox instance</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">3. Fetch next pages via NetmeraInbox object</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">4. Update the status of push notifications</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">5. Get count for one or more status</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>
</html>	
