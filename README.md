# Netmera SDK Sample

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
</div>
<div class="magic-block-callout type-info ">
	<h3><i title="Info" class="fa fa-info-circle"></i>PROGUARD
	</h3>
	<div class="callout-body"><p>This step is crucial if you use proguard:<br>Netmera uses Gson for json operations. Therefore, make sure that you have added up-to-date proguard rules of Gson to your configuration file.<br>Currently list of rules are <a href="https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg" target="_self">here</a> but you should test it and see Netmera is working properly.</p>

	</div>
</div></section><section ng-show="ss.toc.length" class="content-toc grid-25"><!-- ngIf: stickyTOC --><div id="sticky8-sticky-wrapper" class="sticky-wrapper"><div sticky="66" ng-if="stickyTOC" class="sticky-toc ng-scope" id="sticky8" style="width: 192px;"><nav><div class=""><ul><li ng-show="ss.toc.length"><a href="" ng-click="scrollTo()" class="tocHeader"><i class="icon icon-text-align-left"></i>Table of Contents</a></li><!-- ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Create A Google Cloud Messaging Configuration</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Integrate SDK</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Initialize Netmera</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --><li ng-repeat="node in ss.toc" class="toc-DIV"> <a href="" ng-click="scrollTo(node.data[0])" class="ng-binding">Enable Popup Presentation</a><!-- ngIf: node.children.length --></li><!-- end ngRepeat: node in ss.toc --></ul></div></nav></div></div><!-- end ngIf: stickyTOC --><!-- ngIf: !stickyTOC --></section></div></div>

