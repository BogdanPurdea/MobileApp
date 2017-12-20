import React from 'react';
import {View, Text, TextInput, StyleSheet, Button, Picker} from 'react-native';

export class AddTransaction extends React.Component{
    static navigationOptions =  ({navigation}) => ({
        title: 'Add',
    });
    realm = this.props.navigation.state.params.realm;
    add(){
        this.realm.write(() => {
            let transactions = this.realm.objects('Transaction');
            this.realm.create('Transaction', {id: transactions.length+1, category: this.state.category.toString(), type: this.state.type.toString(), value: parseInt(this.state.value)});
        });
        this.props.navigation.navigate('List');
    }
    state = {type: ''};
    render() {
        return (
            <View style = {styles.container}>
                <Text> Category</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(category) => this.setState({category})}
                />
                <Text> Type</Text>
                <Picker
                    selectedValue={this.state.type}
                    onValueChange={(type) => this.setState({type: type})}>
                    <Picker.Item label="Please choose a type" value=""/>
                    <Picker.Item label="Expense" value="Expense" />
                    <Picker.Item label="Income" value="Income" />
                </Picker>
                <Text> Value</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(value) => this.setState({value})}
                />
                <Button
                    onPress={() => this.add()}
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