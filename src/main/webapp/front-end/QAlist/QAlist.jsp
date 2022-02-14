<%@page import="com.qalist.model.QAListVO"%>
<%@page import="com.qalist.model.QAListService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
QAListService qaListService = new QAListService();
List<QAListVO> list = qaListService.getAll();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="zh-Hant-TW">

<head>
    <meta charset="utf-8">
    <!--手機解析度-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!--TDK-->
    <title>常見問題QA</title>

    <!--css-->
    <link rel="stylesheet" type="text/css" href="https://www.ibest.com.tw/css/master.css?v=0203_2">
    <link rel="stylesheet" type="text/css" href="https://www.ibest.com.tw/css/faq.css">

    <script src="https://www.ibest.com.tw/database/function/jquery-1.12.4.min.js"></script>
    <!--inline ajax_function-->

    <!-- 預設CSS -->
    <style>
        abbr,
        address,
        article,
        aside,
        audio,
        b,
        blockquote,
        body,
        canvas,
        caption,
        cite,
        code,
        dd,
        del,
        details,
        dfn,
        div,
        dl,
        dt,
        em,
        fieldset,
        figcaption,
        figure,
        footer,
        form,
        h1,
        h2,
        h3,
        h4,
        h5,
        h6,
        header,
        hgroup,
        html,
        i,
        iframe,
        img,
        ins,
        kbd,
        label,
        legend,
        li,
        mark,
        menu,
        nav,
        object,
        ol,
        p,
        pre,
        q,
        samp,
        section,
        small,
        span,
        strong,
        sub,
        summary,
        sup,
        table,
        tbody,
        td,
        tfoot,
        th,
        thead,
        time,
        tr,
        ul,
        var,
        video {
            margin: 0;
            padding: 0;
            border: 0;
            outline: 0;
            font-size: 100%;
            vertical-align: baseline;
            background: 0 0
        }

        article,
        aside,
        details,
        figcaption,
        figure,
        footer,
        header,
        hgroup,
        menu,
        nav,
        section {
            display: block
        }

        blockquote,
        q {
            quotes: none
        }

        blockquote:after,
        blockquote:before,
        q:after,
        q:before {
            content: '';
            content: none
        }

        * {
            -webkit-text-size-adjust: none
        }

        a {
            text-decoration: none
        }

        em {
            font-style: normal
        }

        fieldset,
        img {
            border: 0
        }

        ol,
        ul {
            list-style: none
        }

        abbr,
        acronym {
            border: 0
        }

        .CLEAR {
            clear: both;
            height: 0;
            font-size: 0;
            line-height: 0
        }

        .clearfix:after {
            content: "";
            display: block;
            height: 0;
            clear: both;
            visibility: hidden
        }

        * html .clearfix {
            height: 1%
        }

        body {
            background-color: #fff;
            font-family: '微軟正黑體', Helvetica, Arial, Heiti TC, "メイリオ", sans-serif;
            font-size: 13px
        }

        body.noScroll {
            overflow-y: hidden
        }

        *,
        ::after,
        ::before {
            -webkit-box-sizing: border-box;
            box-sizing: border-box
        }

        :focus {
            outline: 0
        }

        a {
            color: #000;
            -webkit-transition: all .4s ease;
            -o-transition: all .4s ease;
            transition: all .4s ease
        }

        a:hover {
            color: #c80c16
        }

        a:focus {
            outline: 0
        }

        .outerWrap {
            overflow: hidden
        }

        .wrapMax {
            max-width: 1320px;
            padding-left: 20px;
            padding-right: 20px;
            margin-left: auto;
            margin-right: auto
        }

        header {
            position: fixed;
            left: 0;
            top: 0;
            z-index: 100;
            width: 100%;
            background-color: #fff;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        @media (max-width:960px) {
            header.out {
                opacity: 0;
                pointer-events: none
            }
        }

        header .wrapMax {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            max-width: 100%;
            height: 80px
        }

        @media (max-width:1440px) {
            header .wrapMax {
                padding-right: 0
            }
        }

        header .logo {
            -ms-flex-negative: 0;
            flex-shrink: 0;
            width: 187px
        }

        header .logo a {
            display: block;
            height: 36px;
            background-image: url("https://www.ibest.com.tw/images/logo.svg");
            background-repeat: no-repeat;
            background-size: contain;
            text-indent: -9999px
        }

        header .rightBox {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: end;
            -ms-flex-pack: end;
            justify-content: flex-end;
            width: calc(100% - 187px)
        }

        @media (max-width:1180px) {
            .headerMenuList {
                display: none
            }
        }

        .headerMenuList ul {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex
        }

        .headerMenuList li {
            margin-left: 25px;
            margin-right: 25px
        }

        .headerMenuList .link {
            position: relative;
            font-size: 16px;
            font-weight: 400;
            letter-spacing: .03em;
            color: #3c3c3c
        }

        .headerMenuList .link.current,
        .headerMenuList .link:hover {
            color: #bb1b21
        }

        .headerMenuList .num {
            position: absolute;
            right: -22px;
            top: -15px;
            width: 25px;
            height: 25px;
            line-height: 25px;
            border-radius: 50%;
            background-color: #bb1b21;
            font-weight: 300;
            font-style: normal;
            font-size: 12px;
            text-align: center;
            color: #fff
        }

        .checkbox2,
        .radio2 {
            position: relative;
            display: -webkit-inline-box;
            display: -ms-inline-flexbox;
            display: inline-flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            margin-right: 15px;
            font-size: 14px;
            letter-spacing: .05em;
            color: #444;
            cursor: pointer
        }

        .checkbox2 input[type=checkbox],
        .checkbox2 input[type=radio],
        .radio2 input[type=checkbox],
        .radio2 input[type=radio] {
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
            visibility: hidden;
            pointer-events: none
        }

        .checkbox2 input[type=checkbox]:checked~i,
        .checkbox2 input[type=radio]:checked~i,
        .radio2 input[type=checkbox]:checked~i,
        .radio2 input[type=radio]:checked~i {
            background-color: #bb1b21
        }

        .checkbox2 input[type=checkbox]:checked~i::before,
        .checkbox2 input[type=radio]:checked~i::before,
        .radio2 input[type=checkbox]:checked~i::before,
        .radio2 input[type=radio]:checked~i::before {
            opacity: 1
        }

        .checkbox2 i,
        .radio2 i {
            position: relative;
            display: inline-block;
            margin-right: 5px;
            width: 18px;
            height: 18px;
            background-color: #c6c6c6;
            border-radius: 50%;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .checkbox2 i::before,
        .radio2 i::before {
            content: '';
            position: absolute;
            left: 50%;
            top: 50%;
            margin-left: -5px;
            margin-top: -4px;
            width: 10px;
            height: 8px;
            background-image: url("https://www.ibest.com.tw/images/icon_check2.svg");
            background-repeat: no-repeat;
            background-size: contain;
            opacity: 0;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .menuArea {
            position: fixed;
            left: 0;
            bottom: 0;
            z-index: 99;
            width: 100%;
            height: calc(100% - 80px);
            overflow-y: auto;
            overflow-x: hidden
        }

        @media (max-width:960px) {
            .menuArea {
                bottom: auto;
                top: 0;
                height: 100vh
            }
        }

        .menuArea:not(.lightBox) {
            opacity: 0;
            pointer-events: none;
            visibility: hidden
        }

        .menuArea:not(.lightBox) .btnClose {
            display: none
        }

        .menuArea:not(.lightBox) .leftBox,
        .menuArea:not(.lightBox) .rightBox {
            width: 50%;
            padding: 5% 5% 3%;
            opacity: 0
        }

        .menuArea:not(.lightBox) .leftBox {
            background-color: #f3f2f2;
            -webkit-transform: translateX(-10%);
            -ms-transform: translateX(-10%);
            transform: translateX(-10%)
        }

        @media (max-width:960px) {
            .menuArea:not(.lightBox) .leftBox {
                display: none
            }
        }

        .menuArea:not(.lightBox) .rightBox {
            background-color: #bb1b21;
            -webkit-transform: translateX(10%);
            -ms-transform: translateX(10%);
            transform: translateX(10%)
        }

        @media (max-width:960px) {
            .menuArea:not(.lightBox) .rightBox {
                width: 100%;
                -webkit-transform: translateX(0);
                -ms-transform: translateX(0);
                transform: translateX(0)
            }
        }

        .menuArea:not(.lightBox) .inner {
            -webkit-transform: translateY(10px);
            -ms-transform: translateY(10px);
            transform: translateY(10px);
            opacity: 0
        }

        .menuArea:not(.lightBox).transition {
            -webkit-transition: opacity .5s;
            -o-transition: opacity .5s;
            transition: opacity .5s
        }

        .menuArea:not(.lightBox).transition .leftBox,
        .menuArea:not(.lightBox).transition .rightBox {
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .menuArea:not(.lightBox).transition .inner {
            -webkit-transition: all .5s .4s;
            -o-transition: all .5s .4s;
            transition: all .5s .4s
        }

        .menuArea.lightBox .rightBox {
            display: none
        }

        .menuArea.show {
            opacity: 1;
            pointer-events: auto;
            visibility: visible
        }

        .menuArea.show .leftBox,
        .menuArea.show .rightBox {
            -webkit-transform: translateX(0);
            -ms-transform: translateX(0);
            transform: translateX(0);
            opacity: 1
        }

        .menuArea.show .inner {
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0);
            opacity: 1
        }

        .menuArea .wrapMax {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            max-width: 100%;
            min-height: 100vh;
            padding: 0
        }

        @media (max-width:960px) {
            .menuArea .wrapMax {
                padding-top: 80px
            }
        }

        .menuArea .menuBottom {
            margin-top: 230px;
            line-height: 2;
            color: #f1d1d3;
            letter-spacing: .02em
        }

        @media (max-width:960px) {
            .menuArea .menuBottom {
                margin-top: 40px
            }
        }

        .menuArea .menuBottom a {
            color: #f1d1d3
        }

        .menuArea .menuBottom a:hover {
            text-decoration: underline
        }

        .menuArea .menuCopyright {
            font-weight: 300;
            color: #e4a4a6
        }

        .menuBox {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex
        }

        @media (max-width:480px) {
            .menuBox {
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column
            }
        }

        .menuList {
            -ms-flex-negative: 0;
            flex-shrink: 0
        }

        .menuList a {
            position: relative;
            display: inline-block;
            padding-top: 8px;
            padding-bottom: 8px;
            margin-bottom: 10px;
            line-height: 1;
            font-weight: 400;
            font-size: 30px;
            letter-spacing: .05em;
            color: #fff;
            background-size: 100% 200%;
            -webkit-transition: background-position .4s, -webkit-transform .4s;
            transition: background-position .4s, -webkit-transform .4s;
            -o-transition: background-position .4s, transform .4s;
            transition: background-position .4s, transform .4s;
            transition: background-position .4s, transform .4s, -webkit-transform .4s;
            background-position: 0 0
        }

        @media (max-width:1023px) {
            .menuList a {
                font-size: 24px
            }
        }

        .menuList a::after {
            content: '';
            position: absolute;
            left: 0;
            bottom: 0;
            width: 100%;
            height: 2px;
            opacity: 0;
            -webkit-transform: translateY(-50px);
            -ms-transform: translateY(-50px);
            transform: translateY(-50px);
            pointer-events: none;
            background-color: #fff;
            -webkit-transition: all .4s;
            -o-transition: all .4s;
            transition: all .4s
        }

        .menuList a:hover {
            background-position: 0 -100%
        }

        .menuList a:hover::after {
            opacity: 1;
            -webkit-transform: translateY(0);
            -ms-transform: translateY(0);
            transform: translateY(0)
        }

        .menu {
            margin-top: 5px;
            margin-left: 120px
        }

        @media (max-width:1280px) {
            .menu {
                margin-left: 60px
            }
        }

        @media (max-width:480px) {
            .menu {
                margin-left: 0
            }
        }

        .menu a {
            display: block;
            padding-top: 7px;
            padding-bottom: 7px;
            font-weight: 300;
            font-size: 16px;
            letter-spacing: .05em;
            color: #fff
        }

        .menu a:hover {
            opacity: .7
        }

        .btnMenu {
            position: relative;
            width: 80px;
            height: 80px;
            border: none;
            text-align: center;
            background: 0 0;
            outline: 0;
            cursor: pointer;
            -webkit-transition: all 1s;
            -o-transition: all 1s;
            transition: all 1s
        }

        .btnMenu span {
            display: -webkit-inline-box;
            display: -ms-inline-flexbox;
            display: inline-flex
        }

        .btnMenu i {
            display: block;
            background-color: #be242a;
            -webkit-transition: all .3s;
            -o-transition: all .3s;
            transition: all .3s
        }

        .btnMenu .open {
            position: absolute;
            left: 50%;
            top: 50%;
            -webkit-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -ms-flex-direction: column;
            flex-direction: column;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            width: 24px;
            height: 18px
        }

        .btnMenu .open i {
            width: 100%;
            height: 2px
        }

        .btnMenu .open i:nth-child(1) {
            -webkit-transition-delay: .61s;
            -o-transition-delay: .61s;
            transition-delay: .61s
        }

        .btnMenu .open i:nth-child(2) {
            -webkit-transition-delay: .49s;
            -o-transition-delay: .49s;
            transition-delay: .49s
        }

        .btnMenu .open i:nth-child(3) {
            -webkit-transition-delay: .37s;
            -o-transition-delay: .37s;
            transition-delay: .37s
        }

        .btnMenu .close {
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%) rotate(45deg);
            -ms-transform: translate(-50%, -50%) rotate(45deg);
            transform: translate(-50%, -50%) rotate(45deg);
            width: 26px;
            height: 26px
        }

        .btnMenu .close i:nth-child(1) {
            position: absolute;
            top: 0;
            left: 9.5px;
            -webkit-transition-delay: 0s;
            -o-transition-delay: 0s;
            transition-delay: 0s;
            height: 0%;
            width: 2px
        }

        .btnMenu .close i:nth-child(2) {
            position: absolute;
            left: 0;
            top: 9.5px;
            -webkit-transition-delay: .25s;
            -o-transition-delay: .25s;
            transition-delay: .25s;
            width: 0%;
            height: 2px
        }

        .btnMenu.show .open i {
            width: 0
        }

        .btnMenu.show .open i:nth-child(1) {
            -webkit-transition-delay: .12s;
            -o-transition-delay: .12s;
            transition-delay: .12s
        }

        .btnMenu.show .open i:nth-child(2) {
            -webkit-transition-delay: .24s;
            -o-transition-delay: .24s;
            transition-delay: .24s
        }

        .btnMenu.show .open i:nth-child(3) {
            -webkit-transition-delay: .36s;
            -o-transition-delay: .36s;
            transition-delay: .36s
        }

        .btnMenu.show .close i:nth-child(1) {
            height: 80%;
            -webkit-transition-delay: .8s;
            -o-transition-delay: .8s;
            transition-delay: .8s
        }

        .btnMenu.show .close i:nth-child(2) {
            width: 80%;
            -webkit-transition-delay: .7s;
            -o-transition-delay: .7s;
            transition-delay: .7s
        }

        .arrowStyle2 {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            width: 55px;
            height: 55px;
            background-color: rgba(255, 255, 255, .15);
            border: 1px solid rgba(255, 255, 255, .3);
            color: #fff
        }

        .arrowStyle2 .icon-arrow {
            width: 18px;
            height: 9px
        }

        .arrowStyle2.nextArrow .icon-arrow {
            -webkit-transform: scaleX(-1);
            -ms-transform: scaleX(-1);
            transform: scaleX(-1)
        }

        .arrowStyle2:hover {
            background-color: #fff;
            color: #bb1b21
        }

        .btn05 a {
            display: block;
            text-align: center;
            padding: 12px 40px;
            font-weight: 500;
            font-size: 14px;
            letter-spacing: .03em;
            color: #fff;
            background-color: #bd2228
        }

        .btn05 a:hover {
            color: #fff;
            background-color: #1c1c1c
        }

        .btn06 a {
            position: relative;
            display: -webkit-inline-box;
            display: -ms-inline-flexbox;
            display: inline-flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            text-align: center;
            min-width: 200px;
            padding: 12px 32px;
            font-size: 14px;
            letter-spacing: .05em;
            color: #fff;
            border: 1px solid #535353
        }

        .btn06 a::after {
            content: '';
            position: absolute;
            right: 12px;
            top: 50%;
            -webkit-transform: translateY(-50%);
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
            width: 7px;
            height: 12px;
            background-image: url('https://www.ibest.com.tw/images/icon_arrow.png');
            background-size: contain
        }

        .btn06 a:hover {
            background-color: #fff;
            border-color: #fff;
            color: #bb1b21
        }

        .btn06 a:hover::after {
            background-image: url('https://www.ibest.com.tw/images/icon_arrow_red.png')
        }

        .btn06.white a {
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            color: #bb1b21;
            background-color: #fff;
            border-color: #fff
        }

        .btn06.white a::after {
            background-image: url('https://www.ibest.com.tw/images/icon_arrow_red.png')
        }

        .btn06.white a:hover {
            background-color: #1c1c1c;
            color: #fff;
            border-color: #1c1c1c
        }

        .btn06.white a:hover::after {
            background-image: url('https://www.ibest.com.tw/images/icon_arrow.png')
        }

        .btn06.whiteOpacity a {
            border-color: rgba(255, 255, 255, .45);
            background-color: rgba(255, 255, 255, .2)
        }

        .btn06.whiteOpacity a:hover {
            background-color: #fff
        }

        .btn06.red a {
            color: #fff;
            background-color: #bb1b21;
            border-color: #bb1b21
        }

        .btn06.red a:hover {
            background-color: #1c1c1c;
            border-color: #1c1c1c
        }

        .btn06.center {
            text-align: center
        }

        .moduleBox {
            position: fixed;
            left: 50%;
            top: 48%;
            z-index: 1005;
            opacity: 0;
            visibility: hidden;
            pointer-events: none;
            -webkit-transition-property: top, opacity, visibility;
            -o-transition-property: top, opacity, visibility;
            transition-property: top, opacity, visibility;
            -webkit-transition-duration: .5s;
            -o-transition-duration: .5s;
            transition-duration: .5s;
            -webkit-transition-timing-function: ease-in-out;
            -o-transition-timing-function: ease-in-out;
            transition-timing-function: ease-in-out
        }

        .moduleBox.show {
            top: 50%;
            opacity: 1;
            visibility: visible;
            pointer-events: auto
        }

        .moduleMask {
            position: fixed;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
            z-index: 1003;
            background-color: rgba(0, 0, 0, .7);
            opacity: 0;
            visibility: hidden;
            pointer-events: none;
            -webkit-transition: all .5s ease-in-out;
            -o-transition: all .5s ease-in-out;
            transition: all .5s ease-in-out
        }

        .moduleMask.show {
            opacity: 1;
            visibility: visible;
            pointer-events: auto
        }

        .formBox.fixed {
            position: fixed;
            left: 0;
            top: 0;
            z-index: 999;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            width: 100%;
            height: 100vh;
            overflow-x: hidden;
            opacity: 0
        }

        @media (min-width:961px) {
            .formBox.fixed {
                overflow-y: auto
            }
        }

        .formBox.fixed .checkArea {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center
        }

        .formBox.fixed form {
            background-color: #fff;
            padding: 115px 100px 30px
        }

        @media (max-width:960px) {
            .formBox.fixed form {
                padding: 0
            }
        }

        .formBox.fixed .ins {
            position: relative;
            width: 100%;
            max-width: 960px;
            margin-top: 30vh;
            background-color: #fff;
            -webkit-transition: margin .5s;
            -o-transition: margin .5s;
            transition: margin .5s
        }

        @media (max-width:960px) {
            .formBox.fixed .ins {
                padding: 65px 20px 20px;
                margin-top: 0;
                overflow-y: auto
            }
        }

        @media (max-width:480px) {
            .formBox.fixed .ins {
                padding-bottom: 60px;
                margin-top: 0;
                overflow-y: auto
            }
        }

        .formBox.show {
            opacity: 1;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .formBox.show .ins {
            margin-top: 10vh
        }

        @media (max-width:960px) {
            .formBox.show .ins {
                margin-top: 0
            }
        }

        .formBox.out {
            opacity: 0
        }

        .formBox.out .ins {
            margin-top: 30vh
        }

        @media (max-width:960px) {
            .formBox.out .ins {
                margin-top: 0
            }
        }

        .formBox .mainTitle {
            margin-bottom: 15px;
            font-weight: 400;
            font-size: 30px;
            letter-spacing: .05em;
            color: #bb1b21
        }

        .formBox .formTitle {
            margin-bottom: 25px;
            padding-bottom: 5px;
            font-weight: 400;
            font-size: 20px;
            letter-spacing: .05em;
            color: #1a1a1a;
            border-bottom: 3px solid #be242a
        }

        .formMask {
            position: fixed;
            left: 0;
            top: 0;
            width: 100%;
            height: 100vh;
            background-color: rgba(0, 0, 0, .8);
            opacity: 0;
            pointer-events: none;
            visibility: hidden;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .formMask.show {
            opacity: 1;
            pointer-events: auto;
            visibility: visible
        }

        .formList {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -ms-flex-wrap: wrap;
            flex-wrap: wrap;
            margin-left: -15px;
            margin-right: -15px;
            padding-bottom: 20px
        }

        .formList li {
            width: 50%;
            padding-left: 15px;
            padding-right: 15px;
            padding-bottom: 30px
        }

        @media (max-width:480px) {
            .formList li {
                width: 100%
            }
        }

        .formList .focus .title {
            font-size: 12px;
            -webkit-transform: translateY(-12px);
            -ms-transform: translateY(-12px);
            transform: translateY(-12px)
        }

        @media (max-width:480px) {
            .formList .sexBox {
                width: 100%;
                margin-top: 20px
            }
        }

        .formList .full {
            width: 100%
        }

        .formList .item {
            position: relative;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex
        }

        @media (max-width:480px) {
            .formList .item {
                -ms-flex-wrap: wrap;
                flex-wrap: wrap
            }
        }

        .formList .title {
            position: absolute;
            left: 0;
            top: 3px;
            font-size: 14px;
            color: #444;
            letter-spacing: .05em;
            pointer-events: none;
            -webkit-transition: all .4s;
            -o-transition: all .4s;
            transition: all .4s
        }

        .formList .title em {
            color: #bb1b21
        }

        .formList .inputBox {
            -webkit-box-flex: 1;
            -ms-flex-positive: 1;
            flex-grow: 1
        }

        .formList input[type=text],
        .formList select,
        .formList textarea {
            width: 100%;
            font-size: 16px;
            font-weight: 300;
            font-family: 'Noto Sans TC', sans-serif;
            border: none;
            background: 0 0;
            border-bottom: 1px solid #dbdada;
            border-radius: 0;
            -webkit-transition: all .5s;
            -o-transition: all .5s;
            transition: all .5s
        }

        .formList input[type=text].error,
        .formList select.error,
        .formList textarea.error {
            border-bottom-color: #bb1b21
        }

        .formList input[type=text]:focus,
        .formList select:focus,
        .formList textarea:focus {
            outline: 0
        }

        .formList input[type=text],
        .formList select {
            height: 40px
        }

        .formList select .first {
            opacity: 0 !important;
            color: #faa
        }

        .formList textarea {
            margin-top: 12px;
            height: 100px
        }

        .formList .checkArea {
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex
        }

        .formList .checkArea .btn05 a {
            padding: 13px 40px
        }

        @media (max-width:480px) {
            .formList .checkArea {
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column
            }
        }

        .formList .checkArea .captchaArea {
            margin-right: 20px
        }

        .formList .checkArea .btn05 {
            width: 100%;
            max-width: 200px
        }

        @media (max-width:480px) {
            .formList .checkArea .btn05 {
                margin-top: 12px
            }
        }

        .fixContact {
            opacity: 0
        }

        .captchaArea {
            position: relative;
            width: 200px;
            height: 46px
        }

        .captchaArea.unlocking .sliderBtn {
            -webkit-transition: none;
            -o-transition: none;
            transition: none
        }

        .captchaArea .note {
            position: absolute;
            left: 0;
            top: 0;
            width: 100%;
            height: 45px;
            background-color: #dedede;
            pointer-events: none
        }

        .captchaArea .note::after {
            content: '請往右滑動解鎖';
            position: absolute;
            right: 20px;
            top: 50%;
            -webkit-transform: translateY(-50%);
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
            font-family: '微軟正黑體';
            font-size: 15px;
            letter-spacing: .08em;
            color: #444
        }

        .sliderBtn {
            position: absolute;
            top: 0;
            left: 0;
            z-index: 2;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            width: 45px;
            height: 45px;
            border: none;
            outline: 0;
            background-color: #bd2228;
            cursor: pointer;
            -webkit-transition: all .6s;
            -o-transition: all .6s;
            transition: all .6s
        }

        .sliderBtn::after {
            content: '';
            width: 8px;
            height: 14px;
            background-image: url(../images/icon_arrow.png);
            background-repeat: no-repeat;
            background-size: contain;
            pointer-events: none
        }

        .sliderBtn .note {
            width: 100%;
            height: 45px;
            background-color: #f4f4f4
        }

        .bannerArea #banner {
            margin-bottom: 30px
        }

        .bannerArea .bannerSlide:not(:first-child) {
            display: none
        }

        .lds-spinner {
            display: inline-block;
            position: relative;
            width: 30px;
            height: 30px
        }

        .lds-spinner div {
            transform-origin: 20px 20px;
            animation: lds-spinner 1.2s linear infinite
        }

        .lds-spinner div:after {
            content: " ";
            display: block;
            position: absolute;
            top: 3px;
            left: 20px;
            width: 2px;
            height: 10px;
            border-radius: 20%;
            background: #ff777c
        }

        .lds-spinner div:nth-child(1) {
            transform: rotate(0);
            animation-delay: -1.1s
        }

        .lds-spinner div:nth-child(2) {
            transform: rotate(30deg);
            animation-delay: -1s
        }

        .lds-spinner div:nth-child(3) {
            transform: rotate(60deg);
            animation-delay: -.9s
        }

        .lds-spinner div:nth-child(4) {
            transform: rotate(90deg);
            animation-delay: -.8s
        }

        .lds-spinner div:nth-child(5) {
            transform: rotate(120deg);
            animation-delay: -.7s
        }

        .lds-spinner div:nth-child(6) {
            transform: rotate(150deg);
            animation-delay: -.6s
        }

        .lds-spinner div:nth-child(7) {
            transform: rotate(180deg);
            animation-delay: -.5s
        }

        .lds-spinner div:nth-child(8) {
            transform: rotate(210deg);
            animation-delay: -.4s
        }

        .lds-spinner div:nth-child(9) {
            transform: rotate(240deg);
            animation-delay: -.3s
        }

        .lds-spinner div:nth-child(10) {
            transform: rotate(270deg);
            animation-delay: -.2s
        }

        .lds-spinner div:nth-child(11) {
            transform: rotate(300deg);
            animation-delay: -.1s
        }

        .lds-spinner div:nth-child(12) {
            transform: rotate(330deg);
            animation-delay: 0s
        }

        @keyframes lds-spinner {
            0% {
                opacity: 1
            }

            100% {
                opacity: 0
            }
        }
    </style>
    <style>
        #big {
            width: 80%;
            
        }
    </style>

</head>

<body>

    <!--other analysis-->


    <div class="wrap">
        <div class="rightBox">
            <!-- rightBox -->
            <ul class="faqList">
                <!--faqList-->
                <%! int count =1; %>
				<c:forEach var="qaListVO" items="${list}">
                <li>
                    <!--data-->
                    <div class='title'>
                        <!--title-->
                        <i class="no">Q <%= count++ %></i>
                        <h3>${qaListVO.question}</h3>
                        <span class="toggleOpen"></span>
                    </div>
                    <!--title end-->
                    <div class="definition textEditor">
                        ${qaListVO.answer}
                    </div>
                </li>
                </c:forEach>

            </ul>
            
<!--             此錯誤不須理會 -->
<!-- <%= count=1 %> -->


        </div><!-- rightBox end -->


		
        <script>
            $(function () {

                $('ul.faqList').find('.title').click(function () {
                    var _this = $(this);
                    var _li = _this.next();
                    _li.slideToggle('normal', function () {
                        if ($(this).css('display') == 'none') {
                            _this.removeClass('current');
                        }
                    });
                    if (_li.css('display') == 'block') {
                        _this.addClass('current');
                    }
                });
            });
        </script>

    </div>



</body>
</html>