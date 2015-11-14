
		//动态拼写选项
		function select(obj, begin,  end) {
			//alert(obj + " : " + begin + " : " + end);
			//首先获得选中的选项
			var allSelect = document.getElementById("selID").innerHTML;
			//得到选项的数组
			var types;
			if (allSelect == "") {  //没选过
				types = new Array();
			} else {
				types = allSelect.split(";");
			}
			 
			var isCurrent = false; //之前是否选过的标记
			//评分的处理
			if (obj == "grade") {
				//首先处理不限
				if (begin == "#" && end == "#"){
					//alert("begin = " + begin + "end = " + end );
					document.getElementById("beginGrade").value="";
					document.getElementById("endGrade").value="";
					//alert("types = " + types.length+ ":" + types);
					var temp = types;
					
					types = new Array();
					for (var i = 0; i < temp.length; ++i) {
						if (temp[i].indexOf("星") == -1) {
							types[types.length] = temp[i];
						}
					}
				} else if (begin == 5) {
					document.getElementById("beginGrade").value="5";
					for (var i = 0; i < types.length; ++i) {
						if (types[i].indexOf("星") != -1) {
							types[i] = "5星";
							isCurrent = true;
							break;
						}
					}
					if (!isCurrent) {
						types[types.length] = "5星";
					}
				} else {
					document.getElementById("beginGrade").value=begin;
					document.getElementById("endGrade").value = "";
					for (var i = 0; i < types.length; ++i) {
						if (types[i].indexOf("星") != -1) {
							types[i] = begin + "星以上";
							isCurrent = true;
							break;
						}
					}
					if (!isCurrent) {
						types[types.length] = begin + "星以上";
					}
				}
			}
			//价格的处理
			if (obj == "price") {
				//不限的处理
				if (begin == "#" && end == "#") {
					document.getElementById("beginPrice").value="";
					document.getElementById("endPrice").value="";
					var temp = types;
					types = new Array();
					for (var i = 0; i < temp.length; ++i) {
						//alert("temp[i] = " + temp[i]);
						if (temp[i].indexOf("元") == -1) {
							types[types.length] = temp[i];
						}
					}
				} else { //有限制
					//先处理开始价
					document.getElementById("beginPrice").value = begin;
					for ( var i = 0; i < types.length; ++i) {
						if (types[i].indexOf("元") != -1) {
							types[i] = begin + "元";
							isCurrent = true;
							break;
						}
					}
					if (!isCurrent) {
						types[types.length] = begin + "元";
					}
					//处理结束价
					if (end == "#") {
						document.getElementById("endPrice").value="";
						for ( var i = 0; i < types.length; ++i) {
							if (types[i].indexOf("元") != -1) {
								types[i] += "以上";
								break;
							}
						}
					} else {
						//alert("end = "+ end  + "types = " + types);
						document.getElementById("endPrice").value=end;
						for ( var i = 0; i < types.length; ++i) {
							if (types[i].indexOf("元") != -1) {
								types[i] = types[i].substring(0, types[i].length - 1) + ("-" + end + "元");
								break;
							}
						}
					}
				}
			}
			
			//处理折扣
			if (obj == "discount") {
				//不限的处理
				if (begin == "#" && end == "#") {
					document.getElementById("beginDiscount").value="";
					document.getElementById("endDiscount").value="";
					var temp = types;
					types = new Array();
					for (var i = 0; i < temp.length; ++i) {
						if (temp[i].indexOf("折") == -1) {
							types[types.length] = temp[i];
						}
					}
				} else { //有限制
					//先处理开始价
					document.getElementById("beginDiscount").value = begin;
					for ( var i = 0; i < types.length; ++i) {
						if (types[i].indexOf("折") != -1) {
							types[i] = begin + "折";
							isCurrent = true;
							break;
						}
					}
					if (!isCurrent) {
						types[types.length] = begin + "折";
					}
					//处理结束价
					if (end == "#") {
						document.getElementById("endDiscount").value ="";
						for ( var i = 0; i < types.length; ++i) {
							if (types[i].indexOf("折") != -1) {
								types[i] += "以上";
								break;
							}
						}
					} else {
						document.getElementById("endDiscount").value=end;
						for ( var i = 0; i < types.length; ++i) {
							if (types[i].indexOf("折") != -1) {
								types[i] = types[i].substring(0, types[i].length - 1) + ("-" + end + "折");
								break;
							}
						}
					}
				}
			}
			//alert("types length = " + types.length);
			//alert("allSelect = " + allSelect);
			//没有任何限制
			if (types.length == 0) {
				document.getElementById("selID").innerHTML = "";
				//选完之后submit一下
				document.getElementById("selectDetail").value = "";
				document.forms[1].submit();
				return;
			}
			//枚举出限制，添加
			allSelect = types[0];
			for ( var i = 1; i < types.length; ++i) {
				allSelect += (";" + types[i]);
			}
			document.getElementById("selID").innerHTML = allSelect;
			
			//选完之后submit一下
			document.getElementById("selectDetail").value = allSelect;
			document.forms[1].submit();
		}