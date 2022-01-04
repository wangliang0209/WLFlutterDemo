import 'package:flutter/material.dart';
import 'package:flutter_boost/flutter_boost.dart';

void main() {
  CustomFlutterBinding();
  runApp(MyApp());
}

///创建一个自定义的Binding，继承和with的关系如下，里面什么都不用写
class CustomFlutterBinding extends WidgetsFlutterBinding
    with BoostFlutterBinding {}

class MyApp extends StatefulWidget {
  @override
  State<StatefulWidget> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static Map<String, FlutterBoostRouteFactory> routerMap = {
    'mainPage': (settings, uniqueId) {
      Map<String, Object> map = settings.arguments as Map<String, Object>;
      String data = map['data'] as String;
      return PageRouteBuilder<dynamic>(
          settings: settings,
          pageBuilder: (_, __, ___) => MainPage(
                data: data,
              ));
    },
    'simplePage': (settings, uniqueId) {
      Logger.log('wangliang simplePage builder');
      Map<String, Object> map = settings.arguments as Map<String, Object>;
      String data = map['data'] as String;
      return PageRouteBuilder<dynamic>(
          settings: settings,
          pageBuilder: (_, __, ___) => SimplePage(
                data: data,
              ));
    },
  };

  Route<dynamic> routeFactory(RouteSettings settings, String uniqueId) {
    Logger.log("routeFactory ${settings.name}");
    FlutterBoostRouteFactory func = routerMap[settings.name];
    if (func == null) {
      Logger.log("routeFactory null");
      return null;
    }
    Logger.log("routeFactory not null");
    return func(settings, uniqueId);
  }

  Widget appBuilder(Widget home) {
    return MaterialApp(
      home: home,
      debugShowCheckedModeBanner: false,

      ///必须加上builder参数，否则showDialog等会出问题
      builder: (_, __) {
        return home;
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return FlutterBoostApp(
      routeFactory,
      appBuilder: appBuilder,
    );
  }
}

class MainPage extends StatelessWidget {
  final Object data;
  const MainPage({Key key, this.data}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Center(child: Text('Main Page $data')),
    );
  }
}

class SimplePage extends StatelessWidget {
  const SimplePage({Key key, Object data}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: Center(child: Text('SimplePage')),
    );
  }
}
