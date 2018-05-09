package qfx.common;

import java.text.SimpleDateFormat;

public class Const {
    public static final String SPLITER = ",";
    public static final int ERROR_501 = 501;
    public static final String GET_FACTOR = "getfactor";
    public static final String GET_HISTORY = "gethistory";
    public static final String MACD = "macd";
    public static final String ManSecuMainKey = "Man.SecuMain";
    public static final String ManFactorNameKey = "Man.FactorName";
    public static final String BasicFactorName = "basic_factor";
    /**例如：ROE.20170101*/
    public static final String FactorKeyFormat = "%s.%s";
    public static final SimpleDateFormat simpleDateYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat simpleDateYYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    public final static String ROI_CURVE_TAG = "alphaH: {";


//  the format json string translates python
    public static final String PY_STRAT = "#coding=utf-8\n" +
        "\n" +
        "\"\"\"\n" +
        "@author: evilXu\n" +
        "@file: template.py\n" +
        "@time: 2017/7/24 20:59\n" +
        "@description: \n" +
        "\"\"\"\n" +
        "import json\n" +
        "from rqalpha.api import *\n" +
        "from datetime import *\n" +
        "\n" +
        "'''\n" +
        "注意：回测区间在命令行设置\n" +
        "'''\n" +
        "_json = '''\n";
    public static final String PY_END = "\n   '''\n" +
            "G_json = json.loads(_json)\n" +
            "\n" +
            "def init(context):\n" +
            "    context.json = G_json\n" +
            "    print(type(context.json),context.json)\n" +
            "    _freq = context.json.get(\"settings\").get(\"freq\")\n" +
            "    _span = int(context.json.get(\"settings\").get(\"span\"))\n" +
            "    _serialNum = int(context.json.get(\"settings\").get(\"serial_number\"))\n" +
            "    if _freq == \"周\":\n" +
            "        scheduler.run_weekly(rebalance, tradingday = _serialNum ,span=_span)\n" +
            "    elif _freq == \"月\":\n" +
            "        scheduler.run_monthly(rebalance, tradingday= _serialNum,span=_span)\n" +
            "    else:\n" +
            "        scheduler.run_daily(rebalance, span=_span)\n" +
            "\n" +
            "# before_trading此函数会在每天策略交易开始前被调用，当天只会被调用一次\n" +
            "def before_trading(context, bar_dict):\n" +
            "    # 生成股票池\n" +
            "    pools = context.json['pool']\n" +
            "    context.stock_list = calc_universe(index=pools[\"index\"],\n" +
            "                                       sector=pools[\"sector\"],\n" +
            "                                       industry=pools[\"industry\"],\n" +
            "                                       exchange=pools[\"exchange\"],\n" +
            "                                       margin=pools[\"margin\"],\n" +
            "                                       dropst=True if pools[\"ST\"] == 1 else False)\n" +
            "\n" +
            "def handle_bar(context, bar_dict):\n" +
            "    pass\n" +
            "\n" +
            "def rebalance(context, bar_dict):\n" +
            "    print(\"准备调仓:\", context.now)\n" +
            "    # 因子数据过滤\n" +
            "    filter_stock_list = filter_by_factor(code_list=context.stock_list,tdate = context.now,\n" +
            "                                         factor_list=context.json['conditions'])\n" +
            "\n" +
            "    # 使用因子排序\n" +
            "    sort_stock_list = sort_by_factor(code_list=filter_stock_list,\n" +
            "                                     tdate=context.now,\n" +
            "                                     sort_info=context.json['sort'])\n" +
            "\n" +
            "    # 个股止损因子\n" +
            "    stop_loss_stock_list = filter_by_factor(code_list=list(context.portfolio.positions.keys())\n" +
            "                                            ,tdate = context.now,\n" +
            "                                         factor_list=context.json['stoploss'])\n" +
            "\n" +
            "    # 大盘止损因子\n" +
            "    max_position = market_stoploss_by_factor(tdate=context.now,\n" +
            "                                             stoploss_conditions=context.json['market_stop']\n" +
            "                                             )\n" +
            "\n" +
            "    # 个股止损和下单列表\n" +
            "    buy_stock_list, sell_stock_list = gen_orders(ordered_signal=sort_stock_list,\n" +
            "                                                 cur_position=list(context.portfolio.positions.keys()),\n" +
            "                                                 stop_loss=stop_loss_stock_list,\n" +
            "                                                 max_position_cnt=10,\n" +
            "                                                 max_position=max_position)\n" +
            "\n" +
            "    # 进行具体交易操作,先卖出股票再买入\n" +
            "    trade_stock(context, sell_stock_list)\n" +
            "    trade_stock(context, buy_stock_list)\n" +
            "\n" +
            "\n" +
            "# after_trading函数会在每天交易结束后被调用，当天只会被调用一次\n" +
            "def after_trading(context):\n" +
            "    pass\n" +
            "\n" +
            "\n" +
            "# 实际进行买卖交易操作\n" +
            "def trade_stock(context, stock_list,):\n" +
            "    for stock,weight in stock_list:\n" +
            "        order_target_percent(stock,weight)\n" +
            "\n" +
            "__config__ = {\n" +
            "    \"base\": {\n" +
            "        \"frequency\": \"1d\",\n" +
            "        \"benchmark\": G_json.get(\"settings\").get(\"benchmark\"),\n" +
            "    }\n" +
            "}";

}
