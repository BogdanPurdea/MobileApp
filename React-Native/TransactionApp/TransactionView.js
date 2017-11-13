import React from 'react';
import {View, Text, TextInput, StyleSheet} from 'react-native';

export class TransactionView extends React.Component{

    category: string;
    type: string;
    value: number;
    constructor(category, type, value) {
        super();
        this.category = category;
        this.type = type;
        this.value = value;
    }
    render() {
        return (
            <View style = {styles.container}>
                <Text>Category</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.category}
                />
                <Text>Type</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.type}
                />
                <Text>Value</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.value}
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