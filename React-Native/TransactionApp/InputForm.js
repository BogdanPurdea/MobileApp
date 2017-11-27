import React from 'react';
import {View, Text, TextInput, StyleSheet, Button, Linking} from 'react-native';
import Transaction from "./Transaction";
export class InputForm extends React.Component{
    static navigationOptions =  ({navigation}) => ({
        title: 'Home',
    });

    constructor(props) {
        super(props);
    }
    static submit(){
        Linking.openURL('mailto://bogdanpurdea14@gmail.com&subject=Submit&body=HI');
    }
    render() {
        return (
            <View style = {styles.container}>
                <Button
                    onPress={() => this.props.navigation.navigate('List')}
                    title="List"
                    color="#841584"
                />
                <Text>Category</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(category) => this.setState({category})}
                />
                <Text>Type</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(type) => this.setState({type})}
                />
                <Text>Value</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(value) => this.setState({value})}
                />
                <Button
                    onPress={() => InputForm.submit()}
                    title="Submit"
                    color="#841584"
                />
            </View>
        );
    }
}
const styles = StyleSheet.create({
    container: {
        marginTop: 25,
        flex: 1,
    },
});