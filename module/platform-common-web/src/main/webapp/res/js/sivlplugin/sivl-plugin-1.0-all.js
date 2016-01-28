/**
* SIVL plugin 简称 SP 
* @author:lixuefeng
* @email:youbff@163.com
***/
(function(win){
	win = win || window;
	win.SP = {};
	SP.version = "1.0";
	//请求地址
	SP.serverUrl="/platform-admin";
	/**
	 * 提供浏览器检测的模块
	 * @author:lixuefeng
	 * @module SP.browser
	 */
	var browser = SP.browser = function(){
		var agent = navigator.userAgent.toLowerCase(),
			opera = win.opera,
			browser = {
			/**
			 * @property {boolean} ie 检测当前浏览器是否为IE
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie ) {
			 *     console.log( '当前浏览器是IE' );
			 * }
			 * ```
			 */
			ie		:  /(msie\s|trident.*rv:)([\w.]+)/.test(agent),

			/**
			 * @property {boolean} opera 检测当前浏览器是否为Opera
			 * @example
			 * ```javascript
			 * if ( SP.browser.opera ) {
			 *     console.log( '当前浏览器是Opera' );
			 * }
			 * ```
			 */
			opera	: ( !!opera && opera.version ),

			/**
			 * @property {boolean} webkit 检测当前浏览器是否是webkit内核的浏览器
			 * @example
			 * ```javascript
			 * if ( SP.browser.webkit ) {
			 *     console.log( '当前浏览器是webkit内核浏览器' );
			 * }
			 * ```
			 */
			webkit	: ( agent.indexOf( ' applewebkit/' ) > -1 ),
			
			//ie 内核
			trident : ( agent.indexOf( ' trident/' ) > -1 ),
			
			//Presto 内核
			presto : ( agent.indexOf( ' presto/' ) > -1 ),
			
			//KHTML 内核 
			khtml : ( agent.indexOf( ' khtml/' ) > -1 ),

			/**
			 * @property {boolean} mac 检测当前浏览器是否是运行在mac平台下
			 * @example
			 * ```javascript
			 * if ( SP.browser.mac ) {
			 *     console.log( '当前浏览器运行在mac平台下' );
			 * }
			 * ```
			 */
			mac	: ( agent.indexOf( 'macintosh' ) > -1 ),

			/**
			 * @property {boolean} quirks 检测当前浏览器是否处于“怪异模式”下
			 * @example
			 * ```javascript
			 * if ( SP.browser.quirks ) {
			 *     console.log( '当前浏览器运行处于“怪异模式”' );
			 * }
			 * ```
			 */
			quirks : ( document.compatMode == 'BackCompat' )
		};

		/**
		* @property {boolean} gecko 检测当前浏览器内核是否是gecko内核
		* @example
		* ```javascript
		* if ( SP.browser.gecko ) {
		*     console.log( '当前浏览器内核是gecko内核' );
		* }
		* ```
		*/
		browser.gecko =( navigator.product == 'Gecko' && !browser.webkit && !browser.opera && !browser.ie);
		
		var version = 0;

		// Internet Explorer 6.0+
		if ( browser.ie ){

			var v1 =  agent.match(/(?:msie\s([\w.]+))/);
			var v2 = agent.match(/(?:trident.*rv:([\w.]+))/);
			if(v1 && v2 && v1[1] && v2[1]){
				version = Math.max(v1[1]*1,v2[1]*1);
			}else if(v1 && v1[1]){
				version = v1[1]*1;
			}else if(v2 && v2[1]){
				version = v2[1]*1;
			}else{
				version = 0;
			}

			browser.ie11Compat = document.documentMode == 11;
			/**
			 * @property { boolean } ie9Compat 检测浏览器模式是否为 IE9 兼容模式
			 * @warning 如果浏览器不是IE， 则该值为undefined
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie9Compat ) {
			 *     console.log( '当前浏览器运行在IE9兼容模式下' );
			 * }
			 * ```
			 */
			browser.ie9Compat = document.documentMode == 9;

			/**
			 * @property { boolean } ie8 检测浏览器是否是IE8浏览器
			 * @warning 如果浏览器不是IE， 则该值为undefined
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie8 ) {
			 *     console.log( '当前浏览器是IE8浏览器' );
			 * }
			 * ```
			 */
			browser.ie8 = !!document.documentMode;

			/**
			 * @property { boolean } ie8Compat 检测浏览器模式是否为 IE8 兼容模式
			 * @warning 如果浏览器不是IE， 则该值为undefined
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie8Compat ) {
			 *     console.log( '当前浏览器运行在IE8兼容模式下' );
			 * }
			 * ```
			 */
			browser.ie8Compat = document.documentMode == 8;

			/**
			 * @property { boolean } ie7Compat 检测浏览器模式是否为 IE7 兼容模式
			 * @warning 如果浏览器不是IE， 则该值为undefined
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie7Compat ) {
			 *     console.log( '当前浏览器运行在IE7兼容模式下' );
			 * }
			 * ```
			 */
			browser.ie7Compat = ( ( version == 7 && !document.documentMode )
					|| document.documentMode == 7 );

			/**
			 * @property { boolean } ie6Compat 检测浏览器模式是否为 IE6 模式 或者怪异模式
			 * @warning 如果浏览器不是IE， 则该值为undefined
			 * @example
			 * ```javascript
			 * if ( SP.browser.ie6Compat ) {
			 *     console.log( '当前浏览器运行在IE6模式或者怪异模式下' );
			 * }
			 * ```
			 */
			browser.ie6Compat = ( version < 7 || browser.quirks );

			/**
			* ie9以上版本
			*/
			browser.ie9above = version > 8;
			/**
			* ie9以下版本
			*/
			browser.ie9below = version < 9;
			/**
			* ie11以上版本
			*/
			browser.ie11above = version > 10;
			/**
			* ie11以下版本
			*/
			browser.ie11below = version < 11;

		}

		// Gecko.
		if ( browser.gecko ){
			var geckoRelease = agent.match( /rv:([\d\.]+)/ );
			if ( geckoRelease )
			{
				geckoRelease = geckoRelease[1].split( '.' );
				version = geckoRelease[0] * 10000 + ( geckoRelease[1] || 0 ) * 100 + ( geckoRelease[2] || 0 ) * 1;
			}
		}
		
		/**
		 * @property { Number } chrome 检测当前浏览器是否为Chrome, 如果是，则返回Chrome的大版本号
		 * @warning 如果浏览器不是chrome， 则该值为undefined
		 * @example
		 * ```javascript
		 * if ( SP.browser.firefox ) {
		 *     console.log( '当前浏览器是Firefox' );
		 * }
		 * ```
		 */
		if (/firefox\/(\d+\.\d)/i.test(agent)) {
			browser.firefox = + RegExp['\x241'];
		}

		/**
		 * @property { Number } chrome 检测当前浏览器是否为Chrome, 如果是，则返回Chrome的大版本号
		 * @warning 如果浏览器不是chrome， 则该值为undefined
		 * @example
		 * ```javascript
		 * if ( SP.browser.chrome ) {
		 *     console.log( '当前浏览器是Chrome' );
		 * }
		 * ```
		 */
		if (/chrome\/(\d+\.\d)/i.test(agent)) {
			browser.chrome = + RegExp['\x241'];
		}

		/**
		 * @property { Number } safari 检测当前浏览器是否为Safari, 如果是，则返回Safari的大版本号
		 * @warning 如果浏览器不是safari， 则该值为undefined
		 * @example
		 * ```javascript
		 * if ( SP.browser.safari ) {
		 *     console.log( '当前浏览器是Safari' );
		 * }
		 * ```
		 */
		if(/(\d+\.\d)?(?:\.\d)?\s+safari\/?(\d+\.\d+)?/i.test(agent) && !/chrome/i.test(agent)){
			browser.safari = + (RegExp['\x241'] || RegExp['\x242']);
		}


		// Opera 9.50+
		if ( browser.opera )
			version = parseFloat( opera.version() );

		// WebKit 522+ (Safari 3+)
		if ( browser.webkit )
			version = parseFloat( agent.match( / applewebkit\/(\d+)/ )[1] );

		/**
		 * @property { Number } version 检测当前浏览器版本号
		 * @remind
		 * <ul>
		 *     <li>IE系列返回值为5,6,7,8,9,10等</li>
		 *     <li>gecko系列会返回10900，158900等</li>
		 *     <li>webkit系列会返回其build号 (如 522等)</li>
		 * </ul>
		 * @example
		 * ```javascript
		 * console.log( '当前浏览器版本号是： ' + SP.browser.version );
		 * ```
		 */
		browser.version = version;

		/**
		 * 取得浏览器名字
		 */
		browser.getBrowerName = function(){
			if(browser.ie){
				return "ie";
			}
			if (browser.opera) {
				return "opera";
			}
			if (browser.chrome) {
				return "chrome";
			}
			if (browser.safari) {
				return "safari";
			}
			if (browser.firefox) {
				return "firefox";
			}
			return "other:"+agent;
		};
		
		/**
		 * 取得浏览器版本
		 */
		browser.getBrowerVersion = function(){
			if (browser.chrome) {
				return browser.chrome;
			}
			if (browser.safari) {
				return browser.safari;
			}
			if (browser.firefox) {
				return browser.firefox;
			}
			return browser.version;
		};
		
		/**
		 * 取得浏览器内核
		 */
		browser.getBrowerCore = function(){
			if(browser.webkit){
				return "webkit";
			}
			if(browser.trident){
				return "trident";
			}
			if(browser.presto){
				return "presto";
			}
			if(browser.khtml){
				return "khtml";
			}
			if(browser.gecko){
				return "gecko";
			}
			return "other:"+agent;
		};
		
		/**
		 * 浏览器使用平台,主要统计pc端，如果为移动端直接是wap中另行统计
		 */
		browser.getBrowerPlatform = function(){
			if(browser.mac){
				return "mac";
			}
			return "pc";
		}
		
		browser.browerName = browser.getBrowerName();
		browser.browerVersion = browser.getBrowerVersion();
		browser.browerCore = browser.getBrowerCore();
		browser.browerPlatform = browser.getBrowerPlatform();
		
		/**
		 * 浏览器信息
		 */
		browser.browerInfo = {
			"browerName":browser.browerName,
			"browerVersion":browser.browerVersion,
			"browerCore":browser.browerCore,
			"browerPlatform":browser.browerPlatform
		};
		
		browser.browerInfoSerializable = "browerName="+browser.browerName+"&browerVersion="+browser.browerVersion+"&browerCore="+browser.browerCore+"&browerPlatform="+browser.browerPlatform;
		
		/**
		 * 此处可以定义本插件兼容的浏览器版本
		 * @property { boolean } isCompatible 检测当前浏览器是否能够与插件良好兼容
		 * @example
		 * ```javascript
		 * if ( SP.browser.isCompatible ) {
		 *     console.log( '浏览器与插件能够良好兼容' );
		 * }
		 * ```
		 */
		browser.isCompatible =
			!browser.mobile && (
			( browser.ie && version >= 6 ) ||
			( browser.gecko && version >= 10801 ) ||
			( browser.opera && version >= 9.5 ) ||
			( browser.air && version >= 1 ) ||
			( browser.webkit && version >= 522 ) ||
			false );
		return browser;
	}();
	
	/**
	* 插件工具集合
	* @author:lixuefeng
	* @module SP.utils
	*/
	var utils = SP.utils = {
		/**
		 * 用给定的迭代器遍历数组或类数组对象
		 * @method each
		 * @param { Array } array 需要遍历的数组或者类数组
		 * @param { Function } iterator 迭代器， 该方法接受两个参数， 第一个参数是当前所处理的value， 第二个参数是当前遍历对象的key
		 * @example
		 * ```javascript
		 * var divs = document.getElmentByTagNames( "div" );
		 *
		 * //output: 0: DIV, 1: DIV ...
		 * SP.utils.each( divs, funciton ( value, key ) {
		 *
		 *     console.log( key + ":" + value.tagName );
		 *
		 * } );
		 * ```
		 */
		each : function(obj, iterator, context) {
			if (obj == null) return;
			if (obj.length === +obj.length) {
				for (var i = 0, l = obj.length; i < l; i++) {
					if(iterator.call(context, obj[i], i, obj) === false)
						return false;
				}
			} else {
				for (var key in obj) {
					if (obj.hasOwnProperty(key)) {
						if(iterator.call(context, obj[key], key, obj) === false)
							return false;
					}
				}
			}
		},
		/**
		 * 获取元素item数组array中首次出现的位置, 如果未找到item， 则返回-1。通过start的值可以指定搜索的起始位置。
		 * @method indexOf
		 * @remind 该方法的匹配过程使用的是恒等“===”
		 * @param { Array } array 需要查找的数组对象
		 * @param { * } item 需要在目标数组中查找的值
		 * @param { int } start 搜索的起始位置
		 * @return { int } 返回item在目标数组array中的start位置之后首次出现的位置， 如果在数组中未找到item， 则返回-1
		 * @example
		 * ```javascript
		 * var item = 1,
		 *     arr = [ 3, 4, 6, 8, 1, 2, 8, 3, 2, 1, 1, 4 ];
		 *
		 * //output: 9
		 * console.log( SP.utils.indexOf( arr, item, 5 ) );
		 * ```
		 */
		indexOf:function (array, item, start) {
			var index = -1;
			start = this.isNumber(start) ? start : 0;
			this.each(array, function (v, i) {
				if (i >= start && v === item) {
					index = i;
					return false;
				}
			});
			return index;
		},
		/**
		 * 移除数组array中所有的元素item
		 * @method removeItem
		 * @param { Array } array 要移除元素的目标数组
		 * @param { * } item 将要被移除的元素
		 * @remind 该方法的匹配过程使用的是恒等“===”
		 * @example
		 * ```javascript
		 * var arr = [ 4, 5, 7, 1, 3, 4, 6 ];
		 *
		 * SP.utils.removeItem( arr, 4 );
		 * //output: [ 5, 7, 1, 3, 6 ]
		 * console.log( arr );
		 *
		 * ```
		 */
		removeItem:function (array, item) {
			for (var i = 0, l = array.length; i < l; i++) {
				if (array[i] === item) {
					array.splice(i, 1);
					i--;
				}
			}
		},
		
		/**
		 * 字符串str以','分隔成数组后，将该数组转换成哈希对象或将字符串数组转换成哈希对象， 其生成的hash对象的key为数组中的元素， value为1
		 * @method listToMap
		 * @warning 该方法在生成的hash对象中，会为每一个key同时生成一个另一个全大写的key。
		 * @param { Array } arr 字符串数组
		 * @return { Object } 转化之后的hash对象
		 * @example
		 * ```javascript
		 *
		 * //output: Object {SPutil: 1, SPUTIL: 1, Hello: 1, HELLO: 1}
		 * console.log( SP.utils.listToMap( 'SPutil,Hello' ) );
		 * console.log( SP.utils.listToMap( [ 'SPutil', 'Hello' ] ) );
		 *
		 * ```
		 */
		listToMap:function (list) {
			if (!list)return {};
			list = utils.isArray(list) ? list : list.split(',');
			for (var i = 0, ci, obj = {}; ci = list[i++];) {
				obj[ci.toUpperCase()] = obj[ci] = 1;
			}
			return obj;
		},
		
		/**
		 * 将str中的html符号转义,将转义“'，&，<，"，>”五个字符
		 * @method unhtml
		 * @param { String } str 需要转义的字符串
		 * @return { String } 转义后的字符串
		 * @example
		 * ```javascript
		 * var html = '<body>&</body>';
		 *
		 * //output: &lt;body&gt;&amp;&lt;/body&gt;
		 * console.log( SP.utils.unhtml( html ) );
		 *
		 * ```
		 */
		unhtml:function (str, reg) {
			return str ? str.replace(reg || /[&<">'](?:(amp|lt|quot|gt|#39|nbsp|#\d+);)?/g, function (a, b) {
				if (b) {
					return a;
				} else {
					return {
						'<':'&lt;',
						'&':'&amp;',
						'"':'&quot;',
						'>':'&gt;',
						"'":'&#39;'
					}[a]
				}

			}) : '';
		},

		/**
		 * 将str中的转义字符还原成html字符
		 * @see SP.utils.unhtml(String);
		 * @method html
		 * @param { String } str 需要逆转义的字符串
		 * @return { String } 逆转义后的字符串
		 * @example
		 * ```javascript
		 *
		 * var str = '&lt;body&gt;&amp;&lt;/body&gt;';
		 *
		 * //output: <body>&</body>
		 * console.log( SP.utils.html( str ) );
		 *
		 * ```
		 */
		html:function (str) {
			return str ? str.replace(/&((g|l|quo)t|amp|#39|nbsp);/g, function (m) {
				return {
					'&lt;':'<',
					'&amp;':'&',
					'&quot;':'"',
					'&gt;':'>',
					'&#39;':"'",
					'&nbsp;':' '
				}[m]
			}) : '';
		},
		
		/**
		 * 动态加载文件到doc中
		 * @method loadFile
		 * @param { DomDocument } document 需要加载资源文件的文档对象
		 * @param { Object } options 加载资源文件的属性集合， 取值请参考代码示例
		 * @example
		 * ```javascript
		 *
		 * SP.utils.loadFile( document, {
		 *     src:"test.js",
		 *     tag:"script",
		 *     type:"text/javascript",
		 *     defer:"defer"
		 * } );
		 *
		 * ```
		 */

		/**
		 * 动态加载文件到doc中，加载成功后执行的回调函数fn
		 * @method loadFile
		 * @param { DomDocument } document 需要加载资源文件的文档对象
		 * @param { Object } options 加载资源文件的属性集合， 该集合支持的值是script标签和style标签支持的所有属性。
		 * @param { Function } fn 资源文件加载成功之后执行的回调
		 * @warning 对于在同一个文档中多次加载同一URL的文件， 该方法会在第一次加载之后缓存该请求，
		 *           在此之后的所有同一URL的请求， 将会直接触发回调。
		 * @example
		 * ```javascript
		 *
		 * SP.utils.loadFile( document, {
		 *     src:"test.js",
		 *     tag:"script",
		 *     type:"text/javascript",
		 *     defer:"defer"
		 * }, function () {
		 *     console.log('加载成功');
		 * } );
		 *
		 * ```
		 */
		loadFile:function () {
			var tmpList = [];

			function getItem(doc, obj) {
				try {
					for (var i = 0, ci; ci = tmpList[i++];) {
						if (ci.doc === doc && ci.url == (obj.src || obj.href)) {
							return ci;
						}
					}
				} catch (e) {
					return null;
				}

			}

			return function (doc, obj, fn) {
				var item = getItem(doc, obj);
				if (item) {
					if (item.ready) {
						fn && fn();
					} else {
						item.funs.push(fn)
					}
					return;
				}
				tmpList.push({
					doc:doc,
					url:obj.src || obj.href,
					funs:[fn]
				});
				if (!doc.body) {
					var html = [];
					for (var p in obj) {
						if (p == 'tag')continue;
						html.push(p + '="' + obj[p] + '"')
					}
					doc.write('<' + obj.tag + ' ' + html.join(' ') + ' ></' + obj.tag + '>');
					return;
				}
				if (obj.id && doc.getElementById(obj.id)) {
					return;
				}
				var element = doc.createElement(obj.tag);
				delete obj.tag;
				for (var p in obj) {
					element.setAttribute(p, obj[p]);
				}
				element.onload = element.onreadystatechange = function () {
					if (!this.readyState || /loaded|complete/.test(this.readyState)) {
						item = getItem(doc, obj);
						if (item.funs.length > 0) {
							item.ready = 1;
							for (var fi; fi = item.funs.pop();) {
								fi();
							}
						}
						element.onload = element.onreadystatechange = null;
					}
				};
				element.onerror = function () {
					throw Error('The load ' + (obj.href || obj.src) + ' fails,check the url settings of file ueditor.config.js ');
				};
				doc.getElementsByTagName("head")[0].appendChild(element);
			}
		}(),
		
		/**
		 * 判断obj对象是否为空
		 * @method isEmptyObject
		 * @param { * } obj 需要判断的对象
		 * @remind 如果判断的对象是NULL， 将直接返回true， 如果是数组且为空， 返回true， 如果是字符串， 且字符串为空，
		 *          返回true， 如果是普通对象， 且该对象没有任何实例属性， 返回true
		 * @return { Boolean } 对象是否为空
		 * @example
		 * ```javascript
		 *
		 * //output: true
		 * console.log( SP.utils.isEmptyObject( {} ) );
		 *
		 * //output: true
		 * console.log( SP.utils.isEmptyObject( [] ) );
		 *
		 * //output: true
		 * console.log( SP.utils.isEmptyObject( "" ) );
		 *
		 * //output: false
		 * console.log( SP.utils.isEmptyObject( { key: 1 } ) );
		 *
		 * //output: false
		 * console.log( SP.utils.isEmptyObject( [1] ) );
		 *
		 * //output: false
		 * console.log( SP.utils.isEmptyObject( "1" ) );
		 *
		 * ```
		 */
		isEmptyObject : function (obj) {
			if (obj == null) return true;
			if (this.isArray(obj) || this.isString(obj)) return obj.length === 0;
			for (var key in obj) if (obj.hasOwnProperty(key)) return false;
			return true;
		},
		
		/**
		 * 把rgb格式的颜色值转换成16进制格式
		 * @method fixColor
		 * @param { String } rgb格式的颜色值
		 * @param { String }
		 * @example
		 * rgb(255,255,255)  => "#ffffff"
		 */
		fixColor:function (name, value) {
			if (/color/i.test(name) && /rgba?/.test(value)) {
				var array = value.split(",");
				if (array.length > 3)
					return "";
				value = "#";
				for (var i = 0, color; color = array[i++];) {
					color = parseInt(color.replace(/[^\d]/gi, ''), 10).toString(16);
					value += color.length == 1 ? "0" + color : color;
				}
				value = value.toUpperCase();
			}
			return  value;
		},
		/**
		 * 克隆对象
		 * @method clone
		 * @param { Object } source 源对象
		 * @return { Object } source的一个副本
		 */

		/**
		 * 深度克隆对象，将source的属性克隆到target对象， 会覆盖target重名的属性。
		 * @method clone
		 * @param { Object } source 源对象
		 * @param { Object } target 目标对象
		 * @return { Object } 附加了source对象所有属性的target对象
		 */
		clone:function (source, target) {
			var tmp;
			target = target || {};
			for (var i in source) {
				if (source.hasOwnProperty(i)) {
					tmp = source[i];
					if (typeof tmp == 'object') {
						target[i] = utils.isArray(tmp) ? [] : {};
						utils.clone(source[i], target[i])
					} else {
						target[i] = tmp;
					}
				}
			}
			return target;
		},
		
		/**
		 * 把cm／pt为单位的值转换为px为单位的值
		 * @method transUnitToPx
		 * @param { String } 待转换的带单位的字符串
		 * @return { String } 转换为px为计量单位的值的字符串
		 * @example
		 * ```javascript
		 *
		 * //output: 500px
		 * console.log( SP.utils.transUnitToPx( '20cm' ) );
		 *
		 * //output: 27px
		 * console.log( SP.utils.transUnitToPx( '20pt' ) );
		 *
		 * ```
		 */
		transUnitToPx : function (val) {
			if (!/(pt|cm)/.test(val)) {
				return val
			}
			var unit;
			val.replace(/([\d.]+)(\w+)/, function (str, v, u) {
				val = v;
				unit = u;
			});
			switch (unit) {
				case 'cm':
					val = parseFloat(val) * 25;
					break;
				case 'pt':
					val = Math.round(parseFloat(val) * 96 / 72);
			}
			return val + (val ? 'px' : '');
		},
		
		/**
		 * 在dom树ready之后执行给定的回调函数
		 * @method domReady
		 * @remind 如果在执行该方法的时候， dom树已经ready， 那么回调函数将立刻执行
		 * @param { Function } fn dom树ready之后的回调函数
		 * @example
		 * ```javascript
		 *
		 * SP.utils.domReady( function () {
		 *
		 *     console.log('123');
		 *
		 * } );
		 *
		 * ```
		 */
		domReady:function () {

			var fnArr = [];

			function doReady(doc) {
				//确保onready只执行一次
				doc.isReady = true;
				for (var ci; ci = fnArr.pop(); ci()) {
				}
			}

			return function (onready) {
				var doc = win.document;
				onready && fnArr.push(onready);
				if (doc.readyState === "complete") {
					doReady(doc);
				} else {
					doc.isReady && doReady(doc);
					if (browser.ie && browser.version != 11) {
						(function () {
							if (doc.isReady) return;
							try {
								doc.documentElement.doScroll("left");
							} catch (error) {
								setTimeout(arguments.callee, 0);
								return;
							}
							doReady(doc);
						})();
						win.attachEvent('onload', function () {
							doReady(doc)
						});
					} else {
						doc.addEventListener("DOMContentLoaded", function () {
							doc.removeEventListener("DOMContentLoaded", arguments.callee, false);
							doReady(doc);
						}, false);
						win.addEventListener('load', function () {
							doReady(doc)
						}, false);
					}
				}

			}
		}(),
		
		/**
		* 排序
		*/
		sort:function(array,compareFn){
			compareFn = compareFn || function(item1, item2){ return item1.localeCompare(item2);};
			for(var i= 0,len = array.length; i<len; i++){
				for(var j = i,length = array.length; j<length; j++){
					if(compareFn(array[i], array[j]) > 0){
						var t = array[i];
						array[i] = array[j];
						array[j] = t;
					}
				}
			}
			return array;
		},
		
		/**
		* 序列号提交参数	
		*/
		serializeParam:function (json) {
			var strArr = [];
			for (var i in json) {
				//忽略默认的几个参数
				if(i=="method" || i=="timeout" || i=="async") continue;
				//传递过来的对象和函数不在提交之列
				if (!((typeof json[i]).toLowerCase() == "function" || (typeof json[i]).toLowerCase() == "object")) {
					strArr.push( encodeURIComponent(i) + "="+encodeURIComponent(json[i]) );
				} else if (utils.isArray(json[i])) {
					//支持传数组内容
					for(var j = 0; j < json[i].length; j++) {
						strArr.push( encodeURIComponent(i) + "[]="+encodeURIComponent(json[i][j]) );
					}
				}
			}
			return strArr.join("&");
		},
		
		/**
		* 格式化请求链接
		*/
		formatUrl:function (url) {
			var u = url.replace(/&&/g, '&');
			u = u.replace(/\?&/g, '?');
			u = u.replace(/&$/g, '');
			u = u.replace(/&#/g, '#');
			u = u.replace(/&+/g, '&');
			return u;
		},
		
		/**
		 * 格式化日期时间
		 * @param date
		 * @param format
		 */
		formatDateTime:function(date,format){
			if(!utils.isDate(date)){
				return undefined;
			}
			var hh = ('0' + date.getHours()).slice(-2),
            ii = ('0' + date.getMinutes()).slice(-2),
            ss = ('0' + date.getSeconds()).slice(-2),
			yyyy = ('000' + date.getFullYear()).slice(-4),
            yy = yyyy.slice(-2),
            mm = ('0' + (date.getMonth()+1)).slice(-2),
            dd = ('0' + date.getDate()).slice(-2);
			format = format || 'yyyy-mm-dd hh:ii:ss';
			return format.replace(/yyyy/ig, yyyy).replace(/yy/ig, yy).replace(/mm/ig, mm).replace(/dd/ig, dd).replace(/hh/ig, hh).replace(/ii/ig, ii).replace(/ss/ig, ss);
		},
		
		/**
		* 是否为跨域请求
		*/
		isCrossDomainUrl:function (url) {
			var a = document.createElement('a');
			a.href = url;
			if (browser.ie) {
				a.href = a.href;
			}
			return !(a.protocol == location.protocol && a.hostname == location.hostname &&
			(a.port == location.port || (a.port == '80' && location.port == '') || (a.port == '' && location.port == '80')));
		},
		
		/**
		* 清理空属性
		*/
		clearEmptyAttrs : function(obj){
			for(var p in obj){
				if(obj[p] === ''){
					delete obj[p]
				}
			}
			return obj;
		},
		
		/**
		* 删除字符串str的首尾空格
		*/
		trim:function (str) {
			return str.replace(/(^[ \t\n\r]+)|([ \t\n\r]+$)/g, '');
		},
		
		/**
		* 字符串转json
		*/
		str2json : function(s){

			if (!utils.isString(s)) return null;
			if (win.JSON) {
				return JSON.parse(s);
			} else {
				return (new Function("return " + utils.trim(s || '')))();
			}
		},
		
		json2str : (function(){

			if (window.JSON) {

				return JSON.stringify;

			} else {

				var escapeMap = {
					"\b": '\\b',
					"\t": '\\t',
					"\n": '\\n',
					"\f": '\\f',
					"\r": '\\r',
					'"' : '\\"',
					"\\": '\\\\'
				};

				function encodeString(source) {
					if (/["\\\x00-\x1f]/.test(source)) {
						source = source.replace(
							/["\\\x00-\x1f]/g,
							function (match) {
								var c = escapeMap[match];
								if (c) {
									return c;
								}
								c = match.charCodeAt();
								return "\\u00"
								+ Math.floor(c / 16).toString(16)
								+ (c % 16).toString(16);
							});
					}
					return '"' + source + '"';
				}

				function encodeArray(source) {
					var result = ["["],
						l = source.length,
						preComma, i, item;

					for (i = 0; i < l; i++) {
						item = source[i];

						switch (typeof item) {
							case "undefined":
							case "function":
							case "unknown":
								break;
							default:
								if(preComma) {
									result.push(',');
								}
								result.push(utils.json2str(item));
								preComma = 1;
						}
					}
					result.push("]");
					return result.join("");
				}

				function pad(source) {
					return source < 10 ? '0' + source : source;
				}

				function encodeDate(source){
					return '"' + source.getFullYear() + "-"
					+ pad(source.getMonth() + 1) + "-"
					+ pad(source.getDate()) + "T"
					+ pad(source.getHours()) + ":"
					+ pad(source.getMinutes()) + ":"
					+ pad(source.getSeconds()) + '"';
				}

				return function (value) {
					switch (typeof value) {
						case 'undefined':
							return 'undefined';

						case 'number':
							return isFinite(value) ? String(value) : "null";

						case 'string':
							return encodeString(value);

						case 'boolean':
							return String(value);

						default:
							if (value === null) {
								return 'null';
							} else if (utils.isArray(value)) {
								return encodeArray(value);
							} else if (utils.isDate(value)) {
								return encodeDate(value);
							} else {
								var result = ['{'],
									encode = utils.json2str,
									preComma,
									item;

								for (var key in value) {
									if (Object.prototype.hasOwnProperty.call(value, key)) {
										item = value[key];
										switch (typeof item) {
											case 'undefined':
											case 'unknown':
											case 'function':
												break;
											default:
												if (preComma) {
													result.push(',');
												}
												preComma = 1;
												result.push(encode(key) + ':' + encode(item));
										}
									}
								}
								result.push('}');
								return result.join('');
							}
					}
				};
			}

		})()
	};
	
	/**
	 * 判断给定的对象是否是字符串
	 * @method isString
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是字符串
	 */

	/**
	 * 判断给定的对象是否是数组
	 * @method isArray
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是数组
	 */

	/**
	 * 判断给定的对象是否是一个Function
	 * @method isFunction
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是Function
	 */

	/**
	 * 判断给定的对象是否是Number
	 * @method isNumber
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是Number
	 */

	/**
	 * 判断给定的对象是否是一个正则表达式
	 * @method isRegExp
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是正则表达式
	 */

	/**
	 * 判断给定的对象是否是一个普通对象
	 * @method isObject
	 * @param { * } object 需要判断的对象
	 * @return { Boolean } 给定的对象是否是普通对象
	 */
	utils.each(['String', 'Function', 'Array', 'Number', 'RegExp', 'Object', 'Date'], function (v) {
		SP.utils['is' + v] = function (obj) {
			return Object.prototype.toString.apply(obj) == '[object ' + v + ']';
		}
	});
	
	/**
	* 提供流量统计模块
	* @author:lixuefeng
	* @module SP.plog
	*/
	var plog = SP.plog = function(){
		plog = {
			//起始时间
			startTime : new Date(),
			//统计页面PV
			pvUrl:SP.serverUrl+"/flow/pv.json",
			//统计页面停留时间
			remainTimeUrl:SP.serverUrl+"/flow/pageRemainTime.json"
		};
		/**
		* 统计页面停留时间
		*/
		plog.remainTime = function(options){
			//浏览器关闭时提交数据
			win.onbeforeunload = function(){
				var data = options.data;
				//合并数据
				data = data+"&startTime="+encodeURIComponent(plog.startTime)+"&endTime="+encodeURIComponent(new Date())+"&type=rt&"+SP.browser.browerInfoSerializable;
				var url = plog.remainTimeUrl+data;
			    var img = new Image();  
			    img.src = url;
				//setTimeout(function(){},1000); 
			}
		};
		/**
		* 统计页面PV
		*/
		plog.pv = function(options){
			var data = options.data;
			var insertData = {"type":"rt"};
			//合并数据
			data = $.extend({}, data, insertData, SP.browser.browerInfo);
			$.ajax({
			  url: plog.pvUrl,
			  data: data,
			  dataType: "json",
			  success:options.callback,
			  error:options.errorback
			});
		};
		
		/**
		 * 浏览统计数据发送
		 * url:请求地址
		 * data:请求数据
		 * callback：成功回调函数
		 * errorback：失败回调函数
		 */
		plog.send = function(options){
			var data = options.data;
			//合并数据
			data = $.extend({}, data,SP.browser.browerInfo);
			$.ajax({
			  url: options.url,
			  data: data,
			  dataType: "json",
			  success:options.callback,
			  error:options.errorback
			});
		}
		
		return plog;
	}();
	
})(window);